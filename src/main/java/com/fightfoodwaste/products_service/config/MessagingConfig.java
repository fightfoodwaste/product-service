package com.fightfoodwaste.products_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    public static final String VERIFY_STOCK_QUEUE_NAME = "verify-stock";
    public static final String VERIFY_STOCK_EXCHANGE_NAME = "verify-stock-exchange";
    public static final String STOCK_VERIFIED_QUEUE_NAME = "stock-verified";
    public static final String STOCK_VERIFIED_ROUTING_KEY = "stock-verified";

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(VERIFY_STOCK_EXCHANGE_NAME);
    }

    @Bean
    org.springframework.amqp.core.Queue stock_verified_queue() {
        return new org.springframework.amqp.core.Queue(STOCK_VERIFIED_QUEUE_NAME, true);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(STOCK_VERIFIED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
