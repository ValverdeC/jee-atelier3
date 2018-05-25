package com.sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserRestController extends AbstracRestController{

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "text/plain")
    private String login(@RequestBody String body) {

        String result = this.post("http://localhost:8090/auth/login", body);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup", produces = "text/plain")
    private String signup(@RequestBody String body) {

        String result = this.post("http://localhost:8090/auth/signup", body);

        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/me", produces = "application/json")
    private String me(@RequestHeader("Authorization") String auto) {

        // /me GET + token
    	this.setHeader("Authorization", auto);
    	ResponseEntity<String> result = this.get("http://localhost:8090/me");
        //@RequestHeader("Autorization")
        return result.getBody();
    }
}