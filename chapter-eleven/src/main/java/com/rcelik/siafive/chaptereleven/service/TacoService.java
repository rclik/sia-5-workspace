package com.rcelik.siafive.chaptereleven.service;

import com.rcelik.siafive.chaptereleven.domain.Taco;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TacoService {
    Flux<Taco> getTacos();

    Mono<Taco> getTaco(Long id);

    Mono<Taco> addTaco(Mono<Taco> tacoMono);
}
