package com.johnfnash.learn.mongodb.config;

import com.johnfnash.learn.mongodb.entity.LoggerInfo;
import com.johnfnash.learn.mongodb.service.LoggerInfoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Aspect
@Configuration
public class LoggerInfoAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoggerInfoService loggerInfoService;

    @Pointcut("execution(* com.johnfnash.learn.mongodb.service.*.get*(..))")
    public void executeService() {
    }

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws ClassNotFoundException {
        Object result = null; // 返回值
        LoggerInfo loggerInfo;

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("方法名称："+methodName);
        Object[] args = joinPoint.getArgs();

        try {
            result = joinPoint.proceed();
            loggerInfo = new LoggerInfo("正确日志", new Date(), args, result, targetName, methodName, "");
            loggerInfoService.addLoggerInfo(loggerInfo);
        } catch (Throwable e) {
            String message = e.getMessage();
            loggerInfo = new LoggerInfo("错误日志", new Date(), args, message, targetName, methodName, "");
            loggerInfoService.addLoggerInfo(loggerInfo);
            logger.error("规则切面发生异常", e);
        }


        return result;
    }

}
