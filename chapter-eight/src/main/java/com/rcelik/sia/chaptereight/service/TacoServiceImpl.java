package com.rcelik.sia.chaptereight.service;

import com.rcelik.sia.chaptereight.data.TacoRepository;
import com.rcelik.sia.chaptereight.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Iterable<Taco> getTacos(int pageIndex) {
        log.debug("[getTacos] pageIndex: {}", pageIndex);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageable);
    }
}
