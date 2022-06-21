package com.lry.flight.entity.controller;

import com.lry.flight.utils.HttpReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ParseFlight {

    @GetMapping("/flight")
    public String flight(){
        HttpReq req = new HttpReq();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id",  "1");
        params.put("name",  "tiger");
        Map<String,String> resMap = req.postJson("http://127.0.0.1:8081/doFlight", params);
        System.out.println(resMap);
        return "success";
    }

    @PostMapping("/doFlight")
    public Map<String, String> doFlight(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("id",  "2");
        params.put("name",  "tiger2");
        return params;
    }
}
