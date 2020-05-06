package com.baeldung.um.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev & !qa")
public class UmDevNotQAConfig {

    public UmDevNotQAConfig() {
        super();
    }

}
