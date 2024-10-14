package com.nhnacademy.router.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account", p -> p.path("/account/**").uri("http://localhost:7070"))
                .route("task", p -> p.path("/task/**").uri("http://localhost:9090")).build();

    }
}
