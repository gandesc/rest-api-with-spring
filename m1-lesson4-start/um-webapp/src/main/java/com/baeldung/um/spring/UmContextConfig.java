package com.baeldung.um.spring;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan({ "com.baeldung.um.persistence.model" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource({ "classpath:settings.properties" })
public class UmContextConfig {

    public UmContextConfig() {
        super();
    }

    // beans

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        return pspc;
    }

}
