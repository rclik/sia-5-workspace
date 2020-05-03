package com.rcelik.sia.chaptereight.data;

import com.rcelik.sia.chaptereight.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
