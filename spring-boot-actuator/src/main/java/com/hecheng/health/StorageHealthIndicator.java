package com.hecheng.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class StorageHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("Check health：", "OK").build();
    }
}
