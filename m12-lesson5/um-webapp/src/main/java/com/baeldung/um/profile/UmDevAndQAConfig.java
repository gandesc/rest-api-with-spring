package com.baeldung.um.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.baeldung.um.profile.components.DevAndQAComponent;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Profile("dev & qa")
@Slf4j
public class UmDevAndQAConfig {

    public UmDevAndQAConfig() {
        super();
    }

    @Bean
    DevAndQAComponent devAndQAComponent() {
        log.info("Registering DevAndQAComponent because both dev and qa profiles are enabled");
        return new DevAndQAComponent();
    }

}
