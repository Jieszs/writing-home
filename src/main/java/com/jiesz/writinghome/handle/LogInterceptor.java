package com.jiesz.writinghome.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;


@Component
public class LogInterceptor implements ClientHttpRequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // 记录下请求内容
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================== 开始请求第三方接口 ==========================================\n")
                .append("Request Time   :").append(LocalDateTime.now().toString()).append("\n")
                .append("URL            :").append(Objects.requireNonNull(request.getMethod()).toString()).append("   ").append(request.getURI().toString()).append("\n");
        if (logger.isDebugEnabled()) {
            request.getHeaders().keySet().forEach((key) -> {
                sb.append("Request Header   :").append(Objects.requireNonNull(request.getHeaders().get(key)).toString()).append("\n");
            });
        }
        sb.append("Request body   : ").append(new String(body, StandardCharsets.UTF_8)).append("\n");
        long startTime = System.currentTimeMillis();
        // 执行请求
        ClientHttpResponse response = execution.execute(request, body);
        // 解析返回结果
        StringBuilder inputStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }
        }
        String resp = inputStringBuilder.length() > 500 ? inputStringBuilder.substring(0, 500) : inputStringBuilder.toString();
        sb.append("Status  : ").append(response.getStatusCode()).append("\n");
        sb.append("Response   :  ").append(resp).append("\n")
                .append("Time-Consuming : ").append(System.currentTimeMillis() - startTime).append("ms\n")
                .append("=========================================== End ===========================================\n");
        // 执行耗时
        if (logger.isDebugEnabled()) {
            logger.debug(sb.toString());
        } else {
            logger.info(sb.toString());
        }
        return response;
    }


    public Logger getLogger() {
        return logger;
    }
}
