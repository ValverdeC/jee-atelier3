package com.sample.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/users")
public class UserRestController extends AbstracRestController{

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    private String login(@RequestBody String body) {

        String result = this.post("http://localhost:8090/auth/login", body);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup", produces = "application/json")
    private String signup(@RequestBody String body) {

        String result = this.post("http://localhost:8090/auth/signup", body);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/me", produces = "application/json")
    private String me(@RequestHeader("Autorization") String auto) {

        // /me GET + token
        String result = this.get("http://localhost:8090/me");
        //@RequestHeader("Autorization")
        return result;
    }
}