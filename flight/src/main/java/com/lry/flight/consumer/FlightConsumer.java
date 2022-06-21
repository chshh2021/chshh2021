package com.lry.flight.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lry.flight.entity.Flight;
import com.lry.flight.mesage.FlightMessage;
import com.lry.flight.service.ConsumerService;
import com.lry.flight.service.FlightService;
import com.lry.flight.utils.DateFormat;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = FlightMessage.QUEUE)
public class FlightConsumer {
    @Autowired
    ConsumerService consumerService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Value("${remote-api:host}")
    private String httpApi;
    private int MAX_MILLI_SECOND = 6000;
    @Autowired
    FlightService flightService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler(isDefault = true)
    public boolean onMessage(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        String strJson = new String(message.getBody());
        //logger.info("[onMessage][Thread ID: {} Message body: {}]", Thread.currentThread().getId(), strJson);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Flight flight = mapper.readValue(strJson, Flight.class);
            int num = 0;
            Integer intNum = flight.getNum();
            if(intNum != null) {
                num = intNum.intValue() * MAX_MILLI_SECOND;
            }

            int prevTime = 0;
            String strTime = flight.getTime();
            DateFormat dateFormat = new DateFormat();
            Long nowTime = dateFormat.getMilliSecond();
            if(strTime != null) {
                prevTime = Integer.valueOf(strTime) + num;
            }

            if(prevTime > nowTime) {
                //sendMessage();
                return false;
            }

            String stCode = flightService.getFlightTicket();
            if(stCode != "200") {
                //sendMessage();
                return true;
            }

        } catch (Exception e) {
            //sendMessage();
            return true;
        }

        return false;
    }

    private boolean sendMessage(Flight message) {
        System.out.println("开始发送消息" + message);
        return true;
        try {
            DateFormat dateFormat = new DateFormat();
            Long nowTime = dateFormat.getMilliSecond();
            message.setTime(nowTime.toString());
            rabbitTemplate.convertAndSend(FlightMessage.EXCHANGE,FlightMessage.ROUTING_KEY, message);
        }
        catch (Exception e) { //Log
            System.out.println("Exception: " + e.getStackTrace());
        }

        return true;
    }
}