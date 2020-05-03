package com.rcelik.sia.chaptersix.data;

import com.rcelik.sia.chaptersix.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
