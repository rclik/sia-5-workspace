package com.rcelik.siafive.chapterthree.order.repository;

import com.rcelik.siafive.chapterthree.order.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
