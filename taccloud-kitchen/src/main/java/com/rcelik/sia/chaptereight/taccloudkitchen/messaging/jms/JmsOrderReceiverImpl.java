//package com.rcelik.sia.chaptereight.taccloudkitchen.messaging.jms;
//
//import com.rcelik.sia.chaptereight.taccloudkitchen.domain.Order;
//import com.rcelik.sia.chaptereight.taccloudkitchen.messaging.OrderReceiver;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component("templateOrderReceiver")
//public class JmsOrderReceiverImpl implements OrderReceiver {
//
//    private final JmsTemplate jmsTemplate;
//
//    @Autowired
//    public JmsOrderReceiverImpl(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//    @Override
//    public Order receiveOrder() {
//        log.debug("[receiveOrder]");
//        Order order = (Order) this.jmsTemplate.receiveAndConvert("tacocloud.order.queue");
//        log.debug("[receiveOrder] received order: {}", order);
//        return order;
//    }
//}
