//package com.rcelik.sia.chaptereight.messaging.jms;
//
//import com.rcelik.sia.chaptereight.domain.Order;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//
//@Service
//@Slf4j
//public final class JmsOrderMessagingServiceImpl implements OrderMessagingService{
//
//    private final JmsTemplate jmsTemplate;
//
//    @Autowired
//    public JmsOrderMessagingServiceImpl(JmsTemplate jmsTemplate) {
//        log.debug("[JmsOrderMessagingServiceImpl] bean is created");
//        this.jmsTemplate = jmsTemplate;
//    }
//
////    @Override
////    public void sendOrder(Order order) {
////        log.debug("[sendOrder] order: {}", order);
////        this.jmsTemplate.send(new MessageCreator() {
////            @Override
////            public Message createMessage(Session session) throws JMSException {
////                // the order class must implement Serializable interface
////                // this is send to default jms destination which is defined in application properties file
////                log.debug("[sendOrder.createMessage] message is being created");
////                return session.createObjectMessage(order);
////            }
////        });
////    }
//
////    // custom message converting operation is done via SAC, it find the bean and uses is for converting messages
////    @Override
////    public void sendOrder(Order order) {
////        log.debug("[sendOrder] order: {}", order);
////        jmsTemplate.convertAndSend("tacocloud.order.queue", order);
////    }
////
////    // add custom header to understand who is the sender of this message
////    private Message addOrderSource(Message message) throws JMSException {
////        message.setStringProperty("X_ORDER_SOURCE", "WEB");
////        return message;
////    }
//}
