package com.baeldung.um.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.baeldung.um.persistence.setup.MyApplicationContextInitializer;

@SpringBootApplication (exclude = JacksonAutoConfiguration.class)
public class UmApp {

    public static void main(final String... args) {
        new SpringApplicationBuilder(UmApp.class).initializers(new MyApplicationContextInitializer())
            .run(args);
    }

}
