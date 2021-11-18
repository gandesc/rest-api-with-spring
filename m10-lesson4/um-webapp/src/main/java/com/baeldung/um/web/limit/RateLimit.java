package com.baeldung.um.web.limit;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * @return rate limit in queries per second
     */
    int value();

    /**
     * @return rate limiter identifier (optional)
     */
    String key() default "";

}