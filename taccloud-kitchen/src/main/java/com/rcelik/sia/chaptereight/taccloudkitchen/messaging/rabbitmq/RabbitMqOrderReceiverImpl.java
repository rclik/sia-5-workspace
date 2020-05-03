package com.rcelik.sia.chaptereight.taccloudkitchen.messaging.rabbitmq;

import com.rcelik.sia.chaptereight.taccloudkitchen.domain.Order;
import com.rcelik.sia.chaptereight.taccloudkitchen.messaging.OrderReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("rabbitmq")
@Component("templateOrderReceiver")
public class RabbitMqOrderReceiverImpl implements OrderReceiver {

    // client do not know anything about routing key or exchange name
    // all it knows is queue name and for Jackson2JsonMessageConverter the name og the received object
    // to map it to a class to convert
    private final static String QUEUE_NAME = "tacocloud.orders";

    // this is allowable delay to get the result from messaging queue
    // this is optional, if it is not given then it i 0, which means whenever a receiver sends receive message request
    // if there is no message in the queue then return null. Otherwise it returns null after waiting as much as
    // given timeout.
    private final static long ALLOWED_DELAY = 300;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqOrderReceiverImpl(RabbitTemplate rabbitTemplate) {
        log.debug("[RabbitMqOrderReceiverImpl] creating rabbitmq bean");
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Order receiveOrder() {
        log.debug("[receiveOrder]");
        Message message = rabbitTemplate.receive(QUEUE_NAME, ALLOWED_DELAY);
        if (message != null) {
            Order order = (Order) rabbitTemplate.getMessageConverter().fromMessage(message);
            log.debug("[receiveOrder] order: {}", order);
            return order;
        }
        return null;
    }
}
