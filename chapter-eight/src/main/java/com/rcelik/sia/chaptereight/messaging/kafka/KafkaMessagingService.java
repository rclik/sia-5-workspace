package com.rcelik.sia.chaptereight.messaging.kafka;

import com.rcelik.sia.chaptereight.domain.Order;
import com.rcelik.sia.chaptereight.messaging.OrderMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Profile("kafka")
@Service
public class KafkaMessagingService implements OrderMessagingService {

    private final static String TOPIC_NAME = "tacocloud_orders_topic";
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public KafkaMessagingService(KafkaTemplate<String, Order> kafkaTemplate) {
        log.debug("[KafkaMessagingService] bean is created");
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        log.debug("[sendOrder] order: {}", order);
        kafkaTemplate.send(TOPIC_NAME, order);
    }

    // to send the order to default topic then use sendDefault method but do not forget to add default topic to the
    // properties file
    private void sendOrderToDefaultTopic(Order order) {
        kafkaTemplate.sendDefault(order);
    }
}
