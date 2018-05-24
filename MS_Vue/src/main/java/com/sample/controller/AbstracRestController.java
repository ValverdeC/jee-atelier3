package com.sample.controller;

import org.springframework.web.client.RestTemplate;
import org.json.JSONString;

public class AbstracRestController {

    RestTemplate restTemplate = new RestTemplate();

    public JSONString get(String urlP){
        JSONString json = restTemplate.getForObject(urlP, JSONString.class);
        return json;
    }

    public JSONString post(String urlP, String request){
        JSONString json = restTemplate.postForObject(urlP, request, JSONString.class);
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
