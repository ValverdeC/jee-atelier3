package com.sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardRestController extends AbstracRestController{

    private String apiUrl =  "http://localhost:8100/";

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public String getCards(){
        ResponseEntity<String> cards = this.get(apiUrl + "cards");

        return cards.getBody();
    }

    @RequestMapping(value = "{cardId}", method = RequestMethod.GET, produces = "application/json")
    public String getCardById(@PathVariable int cardId){
    	ResponseEntity<String> cards = this.get(apiUrl + "card/" + cardId);
        return cards.getBody();
    }

    @RequestMapping(value = "search/{cardName}", method = RequestMethod.GET, produces = "application/json")
    public String getCardByName(@PathVariable String cardName){
    	ResponseEntity<String> cards = this.get(apiUrl + "cards/search" + cardName);
        return cards.getBody();
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
