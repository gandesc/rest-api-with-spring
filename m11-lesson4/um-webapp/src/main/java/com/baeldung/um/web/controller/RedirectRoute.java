package com.baeldung.um.web.controller;

import static com.baeldung.um.util.UmMappings.Singular.PRIVILEGE;
import static com.baeldung.um.util.UmMappings.Singular.ROLE;
import static com.baeldung.um.util.UmMappings.Singular.USER;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RedirectRoute {

    @Bean
    RouterFunction<ServerResponse> routerFunction(RedirectHandler redirectHandler) {
        return 
            route(GET(PRIVILEGE), redirectHandler::singularToPlural)
            .andRoute(GET(USER), redirectHandler::singularToPlural)
            .andRoute(GET(ROLE), redirectHandler::singularToPlural);
    }
}
