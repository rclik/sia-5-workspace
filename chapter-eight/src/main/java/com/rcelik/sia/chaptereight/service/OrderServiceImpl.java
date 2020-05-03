package com.rcelik.sia.chaptereight.service;

import com.rcelik.sia.chaptereight.data.OrderRepository;
import com.rcelik.sia.chaptereight.domain.Order;
import com.rcelik.sia.chaptereight.exception.OrderNotFoundException;
import com.rcelik.sia.chaptereight.messaging.OrderMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMessagingService orderMessagingService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMessagingService orderMessagingService) {
        log.debug("[OrderServiceImpl] bean is created");
        this.orderRepository = orderRepository;
        this.orderMessagingService = orderMessagingService;
    }

    @Override
    public Iterable<Order> getOrders() {
        log.debug("[getOrders]");
        Iterable<Order> orders = orderRepository.findAll();

        if (orders == null)
            throw new OrderNotFoundException();

        orders.forEach(order -> orderMessagingService.sendOrder(order));

        return orders;
    }
}
