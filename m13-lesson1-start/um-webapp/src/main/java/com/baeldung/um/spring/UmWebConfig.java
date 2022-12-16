package com.baeldung.um.spring;

import com.baeldung.um.web.controller.PrivilegeController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan({ "com.baeldung.common.web", "com.baeldung.um.web" })
@EnableAspectJAutoProxy
public class UmWebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api/v2", HandlerTypePredicate.forAssignableType(PrivilegeController.class));
        configurer.addPathPrefix("api", HandlerTypePredicate.forAnnotation(RestController.class));
    }
}