package com.baeldung.um.web.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.baeldung.um.util.UmMappings;

import reactor.core.publisher.Mono;

@Configuration
public class RedirectRoute {

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return
            route(GET(UmMappings.Singular.PRIVILEGE), this::singularToPluralHandler)
            .andRoute(GET(UmMappings.Singular.ROLE), this::singularToPluralHandler)
            .andRoute(GET(UmMappings.Singular.USER), this::singularToPluralHandler);
    }

    public Mono<ServerResponse> singularToPluralHandler(ServerRequest request) {

        String correctUri = request.uri().toString() + "s";

        return ServerResponse
            .status(HttpStatus.MOVED_PERMANENTLY)
            .header(org.apache.http.HttpHeaders.LOCATION, correctUri)
            .build();
    }
}