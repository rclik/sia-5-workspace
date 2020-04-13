package com.rcelik.sia.chaptersix.data;

import com.rcelik.sia.chaptersix.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
