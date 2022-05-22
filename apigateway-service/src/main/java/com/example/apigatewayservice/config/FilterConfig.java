package com.example.apigatewayservice.config;

import com.example.apigatewayservice.filter.CustomFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sskim on 2022/05/21
 */
//@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final CustomFilter customFilter;

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec ->
                        predicateSpec.path("/first-service/**")
                                .filters(
                                        gatewayFilterSpec -> gatewayFilterSpec.addRequestHeader("first-request", "first-request-header")
                                                .addResponseHeader("first-response", "first-response-header")
                                                .filter(customFilter.apply(new CustomFilter.Config())))
                                .uri("http://localhost:8081"))
                .route(predicateSpec ->
                        predicateSpec.path("/second-service/**")
                                .filters(
                                        gatewayFilterSpec -> gatewayFilterSpec.addRequestHeader("second-request", "second-request-header")
                                                .addResponseHeader("second-response", "second-response-header")
                                                .filter(customFilter.apply(new CustomFilter.Config())))
                                .uri("http://localhost:8082"))
                .build();
    }

}
