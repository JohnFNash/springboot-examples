package com.johnfnash.learn.springboot.ratelimiter.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnfnash.learn.springboot.ratelimiter.annotation.Limit;

@RestController
public class LimiterController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    @Limit(key = "test", period = 20, count = 10)
    // 意味著 100S 内最多允許訪問10次
    @GetMapping("/test")
    public int testLimiter() {
        return ATOMIC_INTEGER.incrementAndGet();
    }
    
}
