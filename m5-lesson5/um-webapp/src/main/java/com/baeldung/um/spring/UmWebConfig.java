package com.baeldung.um.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({ "com.baeldung.common.web", "com.baeldung.um.web" })
public class UmWebConfig  extends WebMvcConfigurerAdapter {

    public UmWebConfig() {
        super();
    }

    // beans

    // template

}
