package com.rcelik.sia.chaptersix.api;

import com.rcelik.sia.chaptersix.data.TacoRepository;
import com.rcelik.sia.chaptersix.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/design",produces = "application/json")
@CrossOrigin(origins = "*")
@Slf4j
@ConfigurationProperties(prefix = "taco.orders")
public class DesignTacoController {

    private final TacoRepository tacoRepository;

    private int pageSize;

    public void setPageSize(int pageSize){
        log.debug("[setPageSize] page siz is {}", pageSize);
        this.pageSize = pageSize;
    }

    @Autowired
    public DesignTacoController(TacoRepository tacoRepository) {
        log.debug("[DesignTacoController] controller is created");
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public Iterable<Taco> getRecentTacos(){
        log.debug("[getRecentTacos] is called");
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by("createdAt").descending());

        return tacoRepository.findAll(pageable).getContent();
    }

//    @GetMapping("/{id}")
//    public Taco getTacoById(@PathVariable("id") Long id){
//        Optional<Taco> taco = tacoRepository.findById(id);
//        if(taco.isPresent()){
//            return taco.get();
//        }
//
//        return null;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoById(@PathVariable("id") Long id){
        log.debug("[getTacoById] id: {}", id);
        Optional<Taco> taco = tacoRepository.findById(id);
        return taco.map(
                value -> new ResponseEntity<>(value, HttpStatus.OK)
        ).orElseGet(
                () -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco createTaco(@RequestBody Taco taco){
        log.debug("[createTaco] taco: {}", taco);
        return tacoRepository.save(taco);
    }
}
