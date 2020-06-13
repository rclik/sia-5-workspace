package com.rcelik.siafive.chaptereleven.service;

import com.rcelik.siafive.chaptereleven.data.TacoRepository;
import com.rcelik.siafive.chaptereleven.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
@ConfigurationProperties(prefix = "taco.orders")
class TacoServiceImpl implements TacoService {
    private int pageSize;
    private final TacoRepository tacoRepository;

    @Autowired
    public TacoServiceImpl(TacoRepository tacoRepository) {
        log.debug("[TacoServiceImpl] bean is created");
        this.tacoRepository = tacoRepository;
    }

    public void setPageSize(int pageSize) {
        log.debug("[setPageSize] pageSize: {}", pageSize);
        this.pageSize = pageSize;
    }

    @Override
    public Flux<Taco> getTacos() {
        Flux<Taco> tacoFlux = Flux.fromIterable(tacoRepository.findAll()).take(pageSize);
        return tacoFlux;
    }

    @Override
    public Mono<Taco> getTaco(Long id) {
        Optional<Taco> taco = tacoRepository.findById(id);
        return taco.map(Mono::just).orElseGet(Mono::empty);
    }

    @Override
    public Mono<Taco> addTaco(Mono<Taco> tacoMono) {
        // return tacoRepository.saveAllTacos(tacoMono);
        return null;
    }
}