package com.sample.controller;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.json.JSONString;

public class AbstracRestController {

    RestTemplate restTemplate = new RestTemplate();

    public String get(String urlP){
        String json = restTemplate.getForObject(urlP, String.class);
        return json;
    }

    public String post(String urlP, String request){
        String json = restTemplate.postForObject(urlP, request, String.class);
        return json;
    }

    public JSONString put(String urlP, String request){
        return null;
    }

    public JSONString delete(String urlP){
        return null;
    }

    public boolean isUserConnected(){
        //TODO
        return true;
    }
}
