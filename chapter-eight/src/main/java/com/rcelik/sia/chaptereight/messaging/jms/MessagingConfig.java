//package com.rcelik.sia.chaptereight.messaging.jms;
//
//import com.rcelik.sia.chaptereight.domain.Order;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableJms
//@Configuration
//@Slf4j
//@Profile("")
//public class MessagingConfig {
//
////    // if you do not create default active mq destination in application properties file
////    // then need to create its bean inside the code like here.
////    // this means when you create property inside properties file, spring will create a destination bean for it
////    @Bean
////    public ActiveMQQueue createActiveMq(){
////        log.debug("[createActiveMq]");
////        return new ActiveMQQueue("tacocloud.order.queue");
////    }
//
//    @Bean
//    public MappingJackson2MessageConverter messageConverter(){
//        log.debug("[messageConverter] converter bean created");
//        // this mapping will be used in order to convert sending object to jms message object
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
//
//        // this map will hold the class data which holds the sending message
//        // if we do not set this mapping object then default implementation is using fully quailified class name to
//        // to convert it
//        Map<String, Class<?>> typeIdMappings = new HashMap<>();
//
//        typeIdMappings.put("order", Order.class);
//
//        messageConverter.setTypeIdMappings(typeIdMappings);
//
//        return messageConverter;
//    }
//
//}
