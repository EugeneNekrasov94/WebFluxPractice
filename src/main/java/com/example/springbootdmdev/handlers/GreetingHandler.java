package com.example.springbootdmdev.handlers;

import com.example.springbootdmdev.domain.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        Flux<Message> map = Flux
                .just("Hello first!",
                        "Then two",
                        "and the last is three")
                .map(Message::new);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(map,Message.class);
    }

    public Mono<ServerResponse> user(ServerRequest serverRequest) {
        String user = serverRequest.queryParam("user").orElse("NOBODY");
        return ServerResponse
                .ok()
                .render("index", Map.of("user",user));
    }
}
