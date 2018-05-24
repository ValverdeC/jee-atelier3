package com.sample.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController extends AbstracRestController{

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    private String login(@RequestBody String email, String password) {
        //TODO

        ///auth/signup POST + JSON(name,email,pwd) => token

        ///auth/login POST + JSON(email,mdp) => retourn token

        // /me GET + token

        //@RequestHeader("Autorization")

        return null;
    }



}