package com.baeldung.um.persistence.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MyApplicationContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MyApplicationContextInitializer() {
        super();
    }

    //

    /**
     * Sets the active profile.
     */
    @Override
    public void initialize(final GenericApplicationContext applicationContext) {
        final ConfigurableEnvironment environment = applicationContext.getEnvironment();
        final String activeProfiles = environment.getProperty("spring.profiles.active");

        if (activeProfiles != null) {
            environment.setActiveProfiles(activeProfiles.split(","));
        }
    }

}
