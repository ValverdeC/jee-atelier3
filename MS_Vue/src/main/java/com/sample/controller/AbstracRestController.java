package com.sample.controller;

import org.springframework.web.client.RestTemplate;

public class AbstracRestController {

    RestTemplate restTemplate = new RestTemplate();

    public String get(String urlP){
    	String json = restTemplate.getForObject(urlP, String.class);
        return json;
    }

    public String post(String urlP, String body){
    	String json = restTemplate.postForObject(urlP, body, String.class);
        return json;
    }

    public String put(String urlP, String request){
        return null;
    }

    public String delete(String urlP){
        return null;
    }
}
