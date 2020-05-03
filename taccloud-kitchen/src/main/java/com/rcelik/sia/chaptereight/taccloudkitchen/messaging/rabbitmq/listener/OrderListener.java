package com.rcelik.sia.chaptereight.taccloudkitchen.messaging.rabbitmq.listener;

import com.rcelik.sia.chaptereight.taccloudkitchen.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("rabbitmqlistener")
@Slf4j
@Component
public class OrderListener {

    @RabbitListener(queues = {"tacocloud.orders"})
    public void receiveOrder(Order order) {
        log.debug("[receiveOrder] order: {}", order);
    }
}
