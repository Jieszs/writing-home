package com.jiesz.writinghome.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Enumeration;

/**
 * 请求日志切面
 *
 * @author
 */
@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("(execution(public * com.jiesz.writinghome.controller..*.*(..)))&& (@within(org.springframework.web.bind.annotation.RestController))")
    public void webLog() {
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //2.最关键的一步:通过这获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] oArr = proceedingJoinPoint.getArgs();
        JSONObject param = new JSONObject();
        if (oArr != null && oArr.length > 0) {
            for (int i = 0; i < oArr.length; i++) {
                if (oArr[i] instanceof MultipartFile || oArr[i] instanceof File) {
                    oArr[i] = "上传文件不打印";
                }
                if (oArr[i] instanceof HttpServletResponse) {
                    oArr[i] = "响应头不打印";
                }
                if (oArr[i] instanceof HttpServletRequest) {
                    oArr[i] = "请求头不打印";
                }
                if (oArr[i] != null) {
                    param.put(parameterNames[i], oArr[i]);
                }
            }
        }
        // 记录下请求内容
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================== Start ==========================================\n")
                .append("Request Time   :").append(LocalDateTime.now().toString()).append("\n")
                .append("URL            :").append(request.getMethod()).append("   ").append(request.getRequestURL()).append("\n");
        try {
            sb.append("Request Args   : ").append(param.toJSONString()).append("\n");
        } catch (Exception e) {
            logger.error("打印请求日志报错，存在无法json化的对象", e);
        }

        if (logger.isDebugEnabled()) {
            sb.append("Request Header   :").append(getHeader(request)).append("\n")
                    .append("Class Method   : ").append(proceedingJoinPoint.getSignature().getDeclaringTypeName()).append(".").append(proceedingJoinPoint.getSignature().getName()).append("\n")
                    .append("IP             : ").append(getIpAddress(request)).append("\n");
        }

        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        String resultJson = "";
        if (result instanceof byte[]) {
            resultJson = "方法的结果是文件流,不输出";
        } else if (result != null) {
            try {
                resultJson = JSON.toJSONString(result);
                if (resultJson.length() > 2000) {
                    resultJson = resultJson.substring(0, 2000) + "...结果过长...";
                }
            } catch (Exception e) {
                logger.error("打印结果日志报错，存在无法json化的对象", e);
            }
        }
        sb.append("Response   :  ").append(resultJson).append("\n")
                .append("Time-Consuming : ").append(System.currentTimeMillis() - startTime).append("ms\n")
                .append("=========================================== End ===========================================\n");
        // 执行耗时
        if (logger.isDebugEnabled()) {
            logger.debug(sb.toString());
        } else {
            logger.info(sb.toString());
        }
        return result;
    }

    private String getHeader(HttpServletRequest request) {
        JSONObject header = new JSONObject();
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            header.put(name, request.getHeader(name));
        }
        return header.toJSONString();
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;

    }
}
