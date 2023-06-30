package com.example.springbootdmdev.config;

import com.example.springbootdmdev.handlers.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

@Configuration
public class GreetingRouter {
/*    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                        .and(RequestPredicates
                                .accept(MediaType.TEXT_PLAIN)),greetingHandler::hello)
                .andRoute(RequestPredicates
                        .GET("/"),
                        serverRequest -> {
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.TEXT_PLAIN)
                            .body(BodyInserters.fromValue("Main Page"));
                        });
    }*/

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                        .and(RequestPredicates
                                .accept(MediaType.TEXT_PLAIN)), greetingHandler::hello)
                .andRoute(RequestPredicates
                                .GET("/"),greetingHandler::user);
    }
}
