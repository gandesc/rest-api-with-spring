package com.baeldung.um.web.ops;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


/* http://localhost:8081/api/actuator/health */

@Component
public class HealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        if (check()) {
            return null;
        }

        return Health.outOfService().build();
    }

    private boolean check() {
        return false;
    }

}