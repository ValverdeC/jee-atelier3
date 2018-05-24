package com.sample.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardRestController extends AbstracRestController{

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public String getCards(){
        //TODO
        return null;
    }

    @RequestMapping(value = "{cardId}", method = RequestMethod.GET, produces = "application/json")
    public String getCardById(@PathVariable int cardId){
        //TODO
        return null;
    }

    @RequestMapping(value = "search/{cardName}", method = RequestMethod.GET, produces = "application/json")
    public String getCardByName(@PathVariable String cardName){
        //TODO
        return null;
    }

    @RequestMapping(value = "sell/{cardId}/{userId}", method = RequestMethod.PUT, produces = "application/json")
    public String sellCard(@PathVariable int cardId, @PathVariable int userId){
        //TODO
        return null;
    }

    @RequestMapping(value = "buy/{cardId}/{userId}", method = RequestMethod.PUT, produces = "application/json")
    public String buyCard(@PathVariable int cardId, @PathVariable int userId){
        //TODO
        return null;
    }

}
