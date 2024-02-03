package com.gateway.auth;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.function.Predicate;

public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/token/acquire",
            "/auth/signup",
            "/auth/authenticate",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured   =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri->request.getURI().getPath().contains(uri));
}
