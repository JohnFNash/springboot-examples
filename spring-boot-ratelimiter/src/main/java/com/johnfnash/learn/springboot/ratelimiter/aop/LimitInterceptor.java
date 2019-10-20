package com.johnfnash.learn.springboot.ratelimiter.aop;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.ImmutableList;
import com.johnfnash.learn.springboot.ratelimiter.annotation.Limit;
import com.johnfnash.learn.springboot.ratelimiter.annotation.LimitType;

@Aspect
@Configuration
public class LimitInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LimitInterceptor.class);
    
    private final String REDIS_SCRIPT = buildLuaScript2();
    
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    
    @Around("execution(public * *(..)) && @annotation(com.johnfnash.learn.springboot.ratelimiter.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Limit limitAnno = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnno.limitType();
        String name = limitAnno.name();
        
        String key = null;
        int limitPeriod = limitAnno.period();
        int limitCount = limitAnno.count();
        switch (limitType) {
        case IP:
            key = getIpAddress();
            break;
        case CUSTOMER:
            // TODO 如果此处想根据表达式或者一些规则生成 请看 一起来学Spring Boot | 第二十三篇：轻松搞定重复提交（分布式锁）
            key = limitAnno.key();
            break;
        default:
            break;
        }
        
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnno.prefix(), key));
        try {
            RedisScript<Number> redisScript = new DefaultRedisScript<Number>(REDIS_SCRIPT, Number.class);
            Number count = redisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
            logger.info("Access try count is {} for name={} and key = {}", count, name, key);
            if(count != null && count.intValue() == 1) {
                return pjp.proceed();
            } else {
                throw new RuntimeException("You have been dragged into the blacklist");
            }
//            if(count != null && count.intValue() <= limitCount) {
//                return pjp.proceed();
//            } else {
//                throw new RuntimeException("You have been dragged into the blacklist");
//            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("server exception");
        }
    }
    
    /**
     * 限流 脚本
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c")
           .append("\nc = redis.call('get', KEYS[1])")
           // 调用不超过最大值，则直接返回
           .append("\nif c and tonumber(c) > tonumber(ARGV[1]) then")
           .append("\nreturn c;")
           .append("\nend")
           // 执行计算器自加
           .append("\nc = redis.call('incr', KEYS[1])")
           .append("\nif tonumber(c) == 1 then")
           // 从第一次调用开始限流，设置对应键值的过期
           .append("\nredis.call('expire', KEYS[1], ARGV[2])")
           .append("\nend")
           .append("\nreturn c;");
        return lua.toString();
    }
    
    /**
     * 限流 脚本（处理临界时间大量请求的情况）
     *
     * @return lua脚本
     */
    private String buildLuaScript2() {
        StringBuilder lua = new StringBuilder();
        lua.append("redis.replicate_commands(); local listLen, time")
           .append("\nlistLen = redis.call('LLEN', KEYS[1])")
           // 不超过最大值，则直接写入时间
           .append("\nif listLen and tonumber(listLen) < tonumber(ARGV[1]) then")
                .append("\nlocal a = redis.call('TIME');")
                .append("\nredis.call('LPUSH', KEYS[1], a[1]*1000000+a[2])")
           .append("\nelse")
               // 取出现存的最早的那个时间，和当前时间比较，看是小于时间间隔
               .append("\ntime = redis.call('LINDEX', KEYS[1], -1)")
               .append("\nlocal a = redis.call('TIME');")
               .append("\nif a[1]*1000000+a[2] - time < tonumber(ARGV[2])*1000000 then")
                   // 访问频率超过了限制，返回0表示失败
                   .append("\nreturn 0;")
               .append("\nelse")                   
                   .append("\nredis.call('LPUSH', KEYS[1], a[1]*1000000+a[2])")
                   .append("\nredis.call('LTRIM', KEYS[1], 0, tonumber(ARGV[1])-1)")
               .append("\nend")   
           .append("\nend")
           .append("\nreturn 1;");
        return lua.toString();
    }
    
    private static final String UNKNOWN = "unknown";

    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
}