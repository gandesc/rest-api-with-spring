package com.baeldung.um.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan({ "com.baeldung.common.web", "com.baeldung.um.web" })
@EnableAspectJAutoProxy
public class UmWebConfig implements WebMvcConfigurer {

    public UmWebConfig() {
        super();
    }

}