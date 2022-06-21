package com.lry.flight.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.lry.flight.entity.Flight;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class HttpReq {

    public Map<String, String> postJson(String apiUrl, Map<String, String> params) {
        Map<String, String> map = new HashMap<>();
        HttpRequest request = HttpRequest.post(apiUrl);
        request.contentType("application/json;charset=utf-8");
        request.charset("utf-8");
        map.put("status", "200");
        map.put("message", "success");
        map.put("data", "");
        if(params != null){
            ObjectMapper mapper = new ObjectMapper();
            String strJson = "";
            try {
                strJson = mapper.writeValueAsString(params);
            }
            catch (Exception e) {
                map.put("status", "500");
                map.put("message", e.getMessage());
                return map;
            }
            request.body(strJson);
        }
        HttpResponse response = request.send();
        int codeNum = response.statusCode();
        if(codeNum != 200) {
            map.put("status", Integer.toString(codeNum));
            map.put("message", "fail");
            return map;
        }
        map.put("status", Integer.toString(codeNum));
        map.put("message", "success");
        map.put("data", response.bodyText());
        return map;
    }

    public Map<String,String> parseFlight(String strJson) {
        Map<String,String> map = new HashMap<String,String>();
        ObjectMapper mapper = new ObjectMapper();
        map.put("errnum", "0");
        map.put("errmsg", "");
        Flight flight = null;
        if(strJson != null) {
            try {
                flight = mapper.readValue(strJson, Flight.class);
                map.put("id", flight.getId().toString());
                map.put("id", flight.getName());
            } catch (Exception e) {
                map.put("errnum", "101");
                map.put("errmsg", e.getMessage());
            }
        }

        return map;
    }
}
