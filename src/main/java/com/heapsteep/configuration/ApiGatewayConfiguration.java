package com.heapsteep.configuration;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
																		.uri("http://httpbin.org/"); //any working URL.
		Function<PredicateSpec, Buildable<Route>> routeFunction2 = p -> p.path("/get")
																		.uri("https://heapsteep.com"); //any working URL.
		
		Function<PredicateSpec, Buildable<Route>> routeFunction3 = p -> p.path("/microservice-2/**")
																		.uri("lb://microservice-2");
		Function<PredicateSpec, Buildable<Route>> routeFunction4 = p -> p.path("/microservice-2-feign/**")
																		.uri("lb://microservice-2");
		
		/*
		 * Function<PredicateSpec, Buildable<Route>> routeFunction5 = p ->
		 * p.path("/microservice-2-new/**") .filters(f -> f.rewritePath(
		 * "/microservice-2-new/", "/microservice-2-feign/"))
		 * .uri("lb://microservice-2");
		 */
		
		return builder.routes()
				 .route(routeFunction)
				 .route(routeFunction2)
				 .route(routeFunction3)
				 .route(routeFunction4)
				 //.route(routeFunction5)
				 .build();
	}
}
