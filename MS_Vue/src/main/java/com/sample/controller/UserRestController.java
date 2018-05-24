package com.sample.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserRestController extends AbstracRestController{

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    private String login(@RequestBody String body) {

        //JSONObject datas = this.parseRequestBody(body);

        //JSONString result = this.post("http://localhost:8090/auth/login", body.toString());

        //return result.toString();
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup", produces = "application/json")
    private String signup(@RequestBody JSONObject body) {

        //JSONString result = this.post("http://localhost:8090/auth/signup", body.toString());

        //return result.toString();
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/me", produces = "application/json")
    private String me(@RequestHeader("Autorization") String auto) {

        // /me GET + token
        //@RequestHeader("Autorization")
        return null;
    }
}