//package com.rcelik.sia.chaptereight.taccloudkitchen.messaging.jms;
//
//import com.rcelik.sia.chaptereight.taccloudkitchen.domain.Order;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class MessagingConfig {
//
//    @Bean
//    public MappingJackson2MessageConverter messageConverter(){
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
//
//        Map<String, Class<?>> typeIdMappings = new HashMap<>();
//        typeIdMappings.put("order", Order.class);
//        messageConverter.setTypeIdMappings(typeIdMappings);
//
//        return messageConverter;
//    }
//}
