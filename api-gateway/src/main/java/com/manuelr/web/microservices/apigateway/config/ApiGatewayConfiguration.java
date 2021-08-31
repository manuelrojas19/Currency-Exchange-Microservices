package com.manuelr.web.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    private static final String CURRENCY_EXCHANGE_PATH = "/currency-exchange/**";
    private static final String CURRENCY_EXCHANGE_URI = "lb://currency-exchange";
    private static final String CURRENCY_CONVERSION_PATH = "/currency-conversion/**";
    private static final String CURRENCY_CONVERSION_URI = "lb://currency-conversion";

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes().
                route(predicateSpec ->
                        predicateSpec.path(CURRENCY_EXCHANGE_PATH).uri(CURRENCY_EXCHANGE_URI)
                ).
                route(predicateSpec ->
                        predicateSpec.path(CURRENCY_CONVERSION_PATH).uri(CURRENCY_CONVERSION_URI)).
                build();
    }
}
