package com.rcelik.sia.chaptereight.data;

import com.rcelik.sia.chaptereight.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
