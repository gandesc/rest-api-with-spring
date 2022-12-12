package com.baeldung.um.web.limit;

import com.baeldung.um.util.limit.JoinPointToStringHelper;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimiterAspect {

    private final Map<String, RateLimiter> limiters = new ConcurrentHashMap<>();

    @Before("@annotation(limit)")
    public void rateLimit(JoinPoint jp, RateLimit limit) {
        String key = getOrCreateKey(jp, limit);
        RateLimiter limiter = limiters.computeIfAbsent(key, name -> RateLimiter.create(limit.value()));
        limiter.acquire();
    }

    private String getOrCreateKey(JoinPoint jp, RateLimit limit) {
        if (limit.key() != null) {
            return limit.key();
        }

        return JoinPointToStringHelper.toString(jp);
    }

}