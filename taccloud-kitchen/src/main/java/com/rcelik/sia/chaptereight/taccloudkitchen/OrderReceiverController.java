package com.rcelik.sia.chaptereight.taccloudkitchen;

import com.rcelik.sia.chaptereight.taccloudkitchen.domain.Order;
import com.rcelik.sia.chaptereight.taccloudkitchen.messaging.OrderReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("rabbitmq")
@Slf4j
@RestController
@RequestMapping(path = "/orders", produces = "application/json")
public class OrderReceiverController {

    private final OrderReceiver orderReceiver;

    @Autowired
    public OrderReceiverController(OrderReceiver orderReceiver) {
        log.debug("[OrderReceiverController]");
        this.orderReceiver = orderReceiver;
    }

    @GetMapping("/receive")
    public Order receiveOrder() {
        log.debug("[receiveOrder]");
        Order order = this.orderReceiver.receiveOrder();

        log.debug("[receiveOrder] receivedOrder: {}", order);
        return order;
    }

}
