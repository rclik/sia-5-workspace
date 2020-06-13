package com.rcelik.siafive.chaptereleven.data;

import com.rcelik.siafive.chaptereleven.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
    // Mono<Taco> saveAllTacos(Mono<Taco> tacoMono);
}
