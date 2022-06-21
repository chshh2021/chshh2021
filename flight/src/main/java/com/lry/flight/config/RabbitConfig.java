package com.lry.flight.config;

import com.lry.flight.mesage.FlightMessage;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static class DirectExchangeConfiguration {
        @Bean
        public Queue queue() {
            return new Queue(FlightMessage.QUEUE, true,false,false);
        }

        @Bean
        public DirectExchange exchange() {
            return new DirectExchange(FlightMessage.EXCHANGE, true, false);
        }

        @Bean
        public Binding binding() {
            return BindingBuilder.bind(queue()).to(exchange()).with(FlightMessage.ROUTING_KEY);
        }
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
