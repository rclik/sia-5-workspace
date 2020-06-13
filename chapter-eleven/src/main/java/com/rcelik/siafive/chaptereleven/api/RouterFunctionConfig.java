package com.rcelik.siafive.chaptereleven.api;

import com.rcelik.siafive.chaptereleven.domain.Taco;
import com.rcelik.siafive.chaptereleven.service.TacoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Profile("fReactive")
@ConfigurationProperties(prefix = "taco.orders")
@Configuration
@Slf4j
public class RouterFunctionConfig {

    private final TacoService tacoService;

    @Autowired
    public RouterFunctionConfig(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @Bean
    public RouterFunction<?> helloRouterFunction(){
        return RouterFunctions.route(
                RequestPredicates.GET("/hello"),
                serverRequest -> ServerResponse.ok().body(Mono.just("Hello functional World!"), String.class)
        ).andRoute(
                RequestPredicates.GET("/bye"),
                serverRequest -> ServerResponse.ok().body(Mono.just("See ya!"), String.class)
        );
    }

    @Bean
    public  RouterFunction<?> tacoRouterFunction(){
        return RouterFunctions.route(
                RequestPredicates.GET("/design/recent"),
                this::recants
        ).andRoute(
                RequestPredicates.POST("/design"),
                this::postTaco
        );
    }

    private Mono<ServerResponse> postTaco(ServerRequest serverRequest) {
        Mono<Taco> tacoMono = serverRequest.bodyToMono(Taco.class);
        Mono<Taco> savedTacoMono = tacoService.addTaco(tacoMono);
        return ServerResponse.ok().body(savedTacoMono, Taco.class);
    }

    private Mono<ServerResponse> recants(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                tacoService.getTacos(),
                Taco.class);
    }
}
