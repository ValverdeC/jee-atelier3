package com.sample.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class AbstracRestController {

    RestTemplate restTemplate = new RestTemplate();

    public String get(String urlP){
    	String json = restTemplate.getForObject(urlP, String.class);
        return json;
    }

    public String post(String urlP, String body) {
    	HttpHeaders httpHeader = new HttpHeaders();
    	httpHeader.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<String> entity = new HttpEntity<String>(body, httpHeader);
    	
    	String json = restTemplate.postForObject(urlP, entity, String.class);
        return json;
    }

    public String put(String urlP, String request){
        return null;
    }

    public String delete(String urlP){
        return null;
    }
}
