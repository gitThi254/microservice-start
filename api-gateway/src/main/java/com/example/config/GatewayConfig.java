package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/v1/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))
                .route("booking-service", r -> r.path("/bookings/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://booking-service"))
                .route("room-service", r -> r.path("/rooms/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://room-service"))
                .build();
    }
}
