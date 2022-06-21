package com.lry.flight.producer;

import com.lry.flight.mesage.FlightMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class FlightProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(String strJson) {
        rabbitTemplate.convertAndSend(FlightMessage.EXCHANGE, FlightMessage.ROUTING_KEY, strJson);
    }
}
