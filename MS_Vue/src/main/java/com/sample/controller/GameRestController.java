package com.sample.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameRestController extends AbstracRestController{

    @RequestMapping(value = "createRoom/{userId}", method = RequestMethod.POST, produces = "application/json")
    public String createRoom(@PathVariable int userId){
        //TODO
        return null;
    }

    @RequestMapping(value = "joinRoom/{roomId}", method = RequestMethod.POST, produces = "application/json")
    public String joinRoom(@PathVariable int roomId){
        //TODO
        return null;
    }
}
