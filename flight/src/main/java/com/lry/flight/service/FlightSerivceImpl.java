package com.lry.flight.service;

import com.lry.flight.utils.HttpReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class FlightSerivceImpl implements FlightService {
    @Value("${remote-api:host}")
    private String hostApi;

    @Async
    @Override
    public String getFlightTicket() {
        HttpReq req = new HttpReq();
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("action", "time");
        Map<String, String> resMap = req.postJson(hostApi, mapParams);
        String stCode = resMap.get("status");
        System.out.println("thread id: " + Thread.currentThread().getId());
        return stCode;
    }

    public void queryAV() {
        /*
        try {
            AV avExample=new AV();
            AvResult avres= avExample.getAvailability("PEK", "SHA", "20081226 00:00:00","CA",true,true);
            System.out.println(avres);
            System.out.println("指定航班可利用座位信息");
            System.out.println(avExample.getAvailByFltNo("CA1501","20090619 00:00:00"));

        } catch(AVNoRoutingException e) {
            AVNoRoutingException 系
        }

        */


    }

}
