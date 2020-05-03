package com.rcelik.sia.chaptereight.messaging.rabbitmq;

import com.rcelik.sia.chaptereight.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Profile("rabbit-mq")
@Slf4j
@Configuration
public class MessagingConfig {
    private static final String MAPPER_NAME = "order";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        log.debug("[jsonMessageConverter] converter object is created");
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper());
        return converter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        log.debug("[classMapper] class mapper bean is created");
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put(MAPPER_NAME, Order.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

}
