package com.baeldung.um.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baeldung.um.security.SimpleCorsFilter;

@Configuration
public class UmServletConfig {

    public UmServletConfig() {
        super();
    }

    // beans    

    @Bean
    public SimpleCorsFilter simpleCorsFilter() {
        return new SimpleCorsFilter();
    }

}
