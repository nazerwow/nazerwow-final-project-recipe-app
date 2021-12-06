package com.ironhack.apigateway;

import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouterLocator(RouteLocatorBuilder builder,
                                            TokenRelayGatewayFilterFactory filterFactory){
        return builder.routes()
                .route(r -> r.path("/api/v1/recipes/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://RECIPE-SERVICE"))
                .route(r -> r.path("/api/v1/cuisines/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://RECIPE-SERVICE"))
                .route(r -> r.path("/api/v1/diets/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://RECIPE-SERVICE"))
                .route(r -> r.path("/api/v1/measurements/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://RECIPE-SERVICE"))
                .route(r -> r.path("/api/v1/favourites/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://FAVOURITES-SERVICE"))
                .route(r -> r.path("/api/v1/ratings/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://RATING-SERVICE"))
                .route(r -> r.path("/api/v1/users/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://USER-SERVICE"))
                .build();
    }
}
