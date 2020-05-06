package com.baeldung.um.profile;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("com.baeldung.um.profile.components")
@Profile("zoo")
public class UmDevOrQAConfig {

    public UmDevOrQAConfig() {
        super();
    }

}
