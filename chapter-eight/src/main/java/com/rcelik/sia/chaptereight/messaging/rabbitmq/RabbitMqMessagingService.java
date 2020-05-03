package com.rcelik.sia.chaptereight.messaging.rabbitmq;

import com.rcelik.sia.chaptereight.domain.Order;
import com.rcelik.sia.chaptereight.messaging.OrderMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile("rabbit-mq")
public class RabbitMqMessagingService implements OrderMessagingService {

    private final static String ROUTING_KEY = "tacocloud.orders";
    private final static String HEADER_ORDER_SOURCE = "X_ORDER_SOURCE";
    private final static String HEADER_ORDER_WEB = "WEB";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqMessagingService(RabbitTemplate rabbitTemplate) {
        log.debug("[RabbitMqMessagingService] service bean is created");
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        log.debug("[sendOrder] order: {}", order);
        // to convert message get the converter, it is used to make Order to Message
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();

        // if you want to add property to add message, use MessageProperties class, take it amqp library
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader(HEADER_ORDER_SOURCE, HEADER_ORDER_WEB);

        // create message object with actual object and messageProperties
        Message message = messageConverter.toMessage(order, messageProperties);

        // send message to message broker with the routing key, default exchange is used
        rabbitTemplate.send(ROUTING_KEY, message);
    }

    private void sendOrderWithConverter(Order order) {
        rabbitTemplate.convertAndSend(ROUTING_KEY, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties props = message.getMessageProperties();
                props.setHeader(HEADER_ORDER_SOURCE, HEADER_ORDER_WEB);
                return message;
            }
        });
    }
}
