package com.sample.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AbstracRestController {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders header = new HttpHeaders();

    public HttpHeaders getHeader() {
		return header;
	}

	public void setHeader(String key, String value) {
		this.header.set(key, value);
	}

	public ResponseEntity<String> get(String urlP) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		HttpEntity entity = new HttpEntity(header);
    	ResponseEntity<String> json = restTemplate.exchange(urlP, HttpMethod.GET, entity, String.class);
        return json;
    }

    public String post(String urlP, String body) {
    	HttpHeaders httpHeader = new HttpHeaders();
    	httpHeader.setContentType(MediaType.APPLICATION_JSON);
    	@SuppressWarnings({ "rawtypes", "unchecked" })
    	HttpEntity entity = new HttpEntity(body, httpHeader);
    	
    	String json = restTemplate.postForObject(urlP, entity, String.class);
    	//ResponseEntity<String> json = restTemplate.exchange(urlP, HttpMethod.POST, entity, String.class);
        return json;
    }

    public String put(String urlP, String request){
        return null;
    }

    public String delete(String urlP){
        return null;
    }
}
