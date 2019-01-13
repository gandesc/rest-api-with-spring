package com.baeldung.um.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.baeldung.um.web.version.CustomRequestMappingHandlerMapping;

@Configuration
@ComponentScan({ "com.baeldung.common.web", "com.baeldung.um.web" })
@EnableWebMvc
public class UmWebConfig implements WebMvcConfigurer {

    public UmWebConfig() {
        super();
    }

    // beans

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new CustomRequestMappingHandlerMapping();
    }
}
