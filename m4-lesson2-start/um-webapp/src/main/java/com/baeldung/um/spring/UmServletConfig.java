package com.baeldung.um.spring;

import com.baeldung.um.security.SimpleCorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UmServletConfig {

    public UmServletConfig() {
        super();
    }

    @Bean
    public SimpleCorsFilter simpleCorsFilter() {
        return new SimpleCorsFilter();
    }

}
