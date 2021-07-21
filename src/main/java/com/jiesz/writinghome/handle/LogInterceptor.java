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
import java.util.Objects;

/**
 * Created by jcl on 2020/12/23
 */
@Component
public class LogInterceptor implements ClientHttpRequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    /**
     * 打印请求信息
     *
     * @param request
     * @param body
     * @throws IOException
     */
    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        logger.info("》》URI         : {}", Objects.requireNonNull(request.getMethod()).toString() + " " + request.getURI().toString());
        request.getHeaders().keySet().forEach((key) -> {
            logger.debug("》》Header      : {}", key + ":" + Objects.requireNonNull(request.getHeaders().get(key)).toString());
        });
        logger.info("》》Request body: {}", new String(body, StandardCharsets.UTF_8));
    }

    /**
     * 响应数据
     *
     * @param response
     * @throws IOException
     */
    private void traceResponse(ClientHttpResponse response) throws IOException {
        logger.info("《《 Status       : {}", response.getStatusCode());
        if (logger.isDebugEnabled()) {
            response.getHeaders().keySet().forEach((key) -> {
                logger.debug("《《 Header      : {}", key + ":" + Objects.requireNonNull(response.getHeaders().get(key)).toString());
            });
        }
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
        logger.info("《《 Response body: {}", resp);
    }

    public Logger getLogger() {
        return logger;
    }
}
