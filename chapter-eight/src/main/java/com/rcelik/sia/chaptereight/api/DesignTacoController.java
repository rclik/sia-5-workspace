package com.rcelik.sia.chaptereight.api;

import com.rcelik.sia.chaptereight.domain.Taco;
import com.rcelik.sia.chaptereight.service.TacoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@Slf4j
@CrossOrigin(origins = "*")
public final class DesignTacoController {
    private final TacoService tacoService;

    @Autowired
    public DesignTacoController(TacoService tacoService) {
        log.debug("[DesignTacoController] taco controller bean is created");
        this.tacoService = tacoService;
    }

    @GetMapping(path = "/recent")
    public final Iterable<Taco> getRecentTacos(){
        log.debug("[getRecentTacos] first page is requested");

        return tacoService.getTacos(0);
    }

}
