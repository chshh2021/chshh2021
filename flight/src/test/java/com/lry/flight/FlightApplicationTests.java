package com.lry.flight;

import com.lry.flight.entity.Flight;
import com.lry.flight.producer.FlightProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class FlightApplicationTests {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
    }

    @Test
    public void testSyncSend() throws InterruptedException {
        /*FlightProducer producer = new FlightProducer();
        int id = (int) (System.currentTimeMillis() / 1000);
        String strJson = "{\"id\":\"50\",\"name\":\"tiger\"}";
        producer.syncSend(strJson);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", strJson);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();*/
    }

    /*@Test
    public void testSyncSend() {
        Flight flight = new Flight();
        flight.setId(100);
        flight.setNum(1);
        flight.setName("tiger");
        flight.setTime("Time");
        rabbitTemplate.convertAndSend(FlightMessage.QUEUE, flight);
        System.out.println("消息发送完毕。");
    }*/

    private void batchSend() {
        Flight flight = new Flight();
        for(int i=0; i<10; i++) {
            flight.setId(100 + i);
            flight.setName("tiger");
            flight.setTime("Time");
            //rabbitTemplate.convertAndSend(FlightMessage.QUEUE, flight);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(100 + i);
        }
    }

}
