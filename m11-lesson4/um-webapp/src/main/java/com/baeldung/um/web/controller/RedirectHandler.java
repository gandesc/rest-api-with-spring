package com.baeldung.um.web.controller;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class RedirectHandler {

    public RedirectHandler() {
        super();
    }

    public Mono<ServerResponse> singularToPlural(ServerRequest request) {
        String correctUri = request.uri()
            .toString() + "s";
        return ServerResponse.status(MOVED_PERMANENTLY)
            .header(LOCATION, correctUri)
            .build();
    }

}
