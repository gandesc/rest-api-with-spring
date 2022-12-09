package com.baeldung.um.spring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/* http://localhost:8081/api/actuator/metrics/service.privilege.findByName */
@Configuration
@ComponentScan({ "com.baeldung.common.metric" })
public class UmMetricConfig {

    public UmMetricConfig() {
        super();
    }

    // beans

    @Bean("privilegeCounter")
    public Counter privilegeCounter(MeterRegistry registry) {
        return registry.counter("service.privilege.findByName");
    }

}
