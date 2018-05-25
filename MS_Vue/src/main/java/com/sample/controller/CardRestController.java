package com.sample.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardRestController extends AbstracRestController{

    private String apiUrl =  "http://localhost:8100/";

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public String getCards(){
        String cards = this.get(apiUrl + "cards");

        return cards;
    }

    @RequestMapping(value = "{cardId}", method = RequestMethod.GET, produces = "application/json")
    public String getCardById(@PathVariable int cardId){
        String card = this.get(apiUrl + "card/" + cardId);
        return card;
    }

    @RequestMapping(value = "search/{cardName}", method = RequestMethod.GET, produces = "application/json")
    public String getCardByName(@PathVariable String cardName){
        String card = this.get(apiUrl + "cards/search" + cardName);
        return card;
    }

    @RequestMapping(value = "sell/{cardId}/{userId}", method = RequestMethod.POST, produces = "application/json")
    public String sellCard(@PathVariable int cardId, @PathVariable int userId, @RequestHeader("Authorization") String autorization){
        //TODO
        return null;
    }

    @RequestMapping(value = "buy/{cardId}/{userId}", method = RequestMethod.POST    , produces = "application/json")
    public String buyCard(@PathVariable int cardId, @PathVariable int userId){
        String result = this.post(apiUrl + "buy/" + cardId + "/" + userId, "");
        return result;
    }

}
