package com.sample.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController extends AbstracRestController{

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    private String login(@RequestBody String email, String password) {
        //TODO
        return null;
    }


}