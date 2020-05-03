package com.rcelik.sia.chaptereight.taccloudkitchen.messaging.kafka;

import com.rcelik.sia.chaptereight.taccloudkitchen.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Profile("kafka")
@Slf4j
@Component("kafkaOrderListener")
public class KafkaOrderListener {

    @KafkaListener(topics = "tacocloud_orders_topic")
    public void handleReceivedOrder(Order order) {
        log.debug("[handleReceivedOrder] order: {}", order);
    }
}
