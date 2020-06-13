package com.rcelik.siafive.chaptereleven.api;

import com.rcelik.siafive.chaptereleven.domain.Taco;
import com.rcelik.siafive.chaptereleven.service.TacoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Profile("!fReactive")
@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
@Slf4j
public class DesignTacoController {

    private final TacoService tacoService;

    @Autowired
    public DesignTacoController(TacoService tacoService) {
        log.debug("[DesignTacoController] taco controller bean is created");
        this.tacoService = tacoService;
    }

    @GetMapping(path = "/recent")
    public final Flux<Taco> getRecentTacos() {
        log.debug("[getRecentTacos] first page is requested");
        return tacoService.getTacos();
    }

    @GetMapping(path = "/{tacoId}")
    public final Mono<Taco> getRecentTacos(@PathVariable Long tacoId) {
        log.debug("[getRecentTacos] tacoId: {} is requested", tacoId);
        return tacoService.getTaco(tacoId);
    }

    @PostMapping(consumes = "application/json")
    public final Mono<Taco> saveTaco(@RequestBody Mono<Taco> tacoMono) {
        return tacoService.addTaco(tacoMono);
    }

}
