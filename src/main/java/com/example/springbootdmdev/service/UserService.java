package com.example.springbootdmdev.service;

import com.example.springbootdmdev.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private WebClient webClient;

    public Mono<User> getUserByIdAsync(String id) {
        return webClient.get()
                .uri(String.join(" ","/users/",id))
                .retrieve()
                .bodyToMono(User.class);
    }

    public User getUserByIdSync(String id) {
        return webClient.get()
                .uri(String.join(" ","/users/",id))
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public Mono<User> getUserByIdAsyncWithRetry(String id) {
        return webClient.get()
                .uri(String.join(" ","/users/",id))
                .retrieve()
                .bodyToMono(User.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1000)));
    }

    public Mono<User> getUserByIdAsyncWithFallback(String id) {
        return webClient.get()
                .uri(String.join(" ","/users/",id))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,error -> Mono.error(new RuntimeException("Clients error")))
                .bodyToMono(User.class)
                .doOnError(throwable -> log.error("Error is {}",throwable.getCause()))
                .onErrorResume(error -> Mono.just(new User("DefName")));
    }
}
