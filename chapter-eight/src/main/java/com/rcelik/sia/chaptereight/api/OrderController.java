package com.rcelik.sia.chaptereight.api;

import com.rcelik.sia.chaptereight.domain.Order;
import com.rcelik.sia.chaptereight.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin("*")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        log.debug("[OrderController]");
        this.orderService = orderService;
    }

    @GetMapping
    public final Iterable<Order> getOrders(){
        log.debug("[getOrders]");
        return this.orderService.getOrders();
    }
}
