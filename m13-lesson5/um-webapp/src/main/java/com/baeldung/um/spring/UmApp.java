package com.baeldung.um.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.baeldung.um.persistence.setup.MyApplicationContextInitializer;

@SpringBootApplication
public class UmApp {

    public static void main(final String... args) {
        new SpringApplicationBuilder(UmApp.class).initializers(new MyApplicationContextInitializer()).listeners().run(args);
    }

}
