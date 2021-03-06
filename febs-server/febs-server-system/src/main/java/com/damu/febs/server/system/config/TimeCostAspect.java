package com.damu.febs.server.system.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class TimeCostAspect {

    private static final String POINT = "execution(* com.damu.febs.server.*.controller..*.*(..))";

    @Pointcut(POINT)
    public void performance() {
    }

    @Around("performance()")
    public Object loggingAround(ProceedingJoinPoint joinPoint) {
        long startTime = 0L;
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            startTime = System.currentTimeMillis();
            // 定义返回对象、得到方法需要的参数
            Object resultData = null;
            Object[] args = joinPoint.getArgs();
            Object apiName = args[0];
            // 调用钉钉接口
            log.info("======>请求[xxx]接口开始,参数:{}", args);
            log.info("请求ip：" + request.getRemoteAddr());
            log.info("请求地址：" + request.getRequestURL().toString());
            log.info("请求方式：" + request.getMethod());
            log.info("请求类方法：" + joinPoint.getSignature());
            resultData = joinPoint.proceed(args);
            long endTime = System.currentTimeMillis();
            log.info("======>请求[xxx]接口完成,耗时:{},返回:{}", (endTime - startTime), resultData);
            return resultData;
        } catch (Throwable e) {
            // 记录异常信息
            long endTime = System.currentTimeMillis();
            log.error("======>请求[xxx]接口异常！耗时:{}", (endTime - startTime));
        }
        return null;
    }

}
