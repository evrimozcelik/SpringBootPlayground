package org.evrim.spring.exam.springboot.web.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AppHealth implements HealthIndicator {
    @Override
    public Health health() {
        return Health.down()
                .withDetail("backend","working")
                .build();
    }
}
