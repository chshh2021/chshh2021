package com.lry.flight.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lry.flight.entity.Flight;
import com.lry.flight.producer.FlightProducer;
import com.lry.flight.utils.HttpReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumerService {
    @Value("${remote-api:host}")
    private String hostApi;

    public String requestRemote(String url, String paramsStr) {
        HttpReq req = new HttpReq();
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("pnr", "E");
        Map<String, String> resMap = req.postJson(hostApi, mapParams);
        String stCode = resMap.get("status");
        System.out.println("StateCode: " + stCode);
        if(!stCode.equals("200")) {
            FlightProducer flightProducer = new FlightProducer();
            flightProducer.syncSend(resMap.get("data"));
            return "fail";
        }
        return "OK";
    }

    public String searchTicket(String strJson) {
        HttpReq req = new HttpReq();
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("pnr", "E");
        Map<String, String> resMap = req.postJson(hostApi, mapParams);
        String stCode = resMap.get("status");
        if(!stCode.equals("200")) {
            FlightProducer flightProducer = new FlightProducer();
            flightProducer.syncSend(resMap.get("data"));
            return "fail";
        }

        return "success";
    }

    public String parseFlight(String strJson) {
        ObjectMapper mapper = new ObjectMapper();
        Flight flight = null;
        try {
            flight = mapper.readValue(strJson, Flight.class);
        } catch (Exception e) {
            return "fail";
        }
        System.out.println("id: " + flight.getId());
        System.out.println("name: " + flight.getName());
        return "sucess";
    }

    public Map<String, ?> parseJsonToMap() {
        Map<String, ?> map = new HashMap<>();
        return map;
    }

}
