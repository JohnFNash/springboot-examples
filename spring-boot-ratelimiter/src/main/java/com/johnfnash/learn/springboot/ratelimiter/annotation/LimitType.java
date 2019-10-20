package com.johnfnash.learn.springboot.ratelimiter.annotation;

// 限制的类型
public enum LimitType {

    /**
     * 自定义key
     */
    CUSTOMER,
    /**
     * 根据请求者IP
     */
    IP;
    
}
