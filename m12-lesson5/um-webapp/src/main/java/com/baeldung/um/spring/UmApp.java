package com.baeldung.um.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.baeldung.um.persistence.setup.MyApplicationContextInitializer;
import com.baeldung.um.profile.UmDevAndQAConfig;
import com.baeldung.um.profile.UmDevNotQAConfig;
import com.baeldung.um.profile.UmDevOrQAConfig;

@SpringBootApplication
public class UmApp {

    private final static Class<?>[] CONFIGS = { // @formatter:off         
            UmApp.class,
			
            UmDevAndQAConfig.class,
            
            UmDevNotQAConfig.class,
            
            UmDevOrQAConfig.class
    }; // @formatter:on

    public static void main(final String... args) {
        final SpringApplication springApplication = new SpringApplication(CONFIGS);
        springApplication.addInitializers(new MyApplicationContextInitializer());
        springApplication.run(args);
    }

}
