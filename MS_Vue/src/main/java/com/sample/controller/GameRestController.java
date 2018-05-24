package com.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sample.model.Room;

@RestController
@RequestMapping("/api/game")
public class GameRestController extends AbstracRestController{

    @RequestMapping(value = "createRoom", method = RequestMethod.POST, produces = "application/json")
    public String createRoom(@RequestBody Room roomCreator) {
    	Gson gson = new Gson();
        return this.post("http://localhost:4200/game/room", gson.toJson(roomCreator));
    }

    @RequestMapping(value = "joinRoom/{roomId}", method = RequestMethod.POST, produces = "application/json")
    public String joinRoom(@PathVariable int roomId){
        //TODO
        return null;
    }
    
    @GetMapping("/rooms")
    public Room[] getRooms() throws Exception {
    	String roomsString = this.get("http://localhost:4200/game/rooms");
    	Gson gson = new Gson();
    	Room rooms[] = gson.fromJson(roomsString, Room[].class);
    	return rooms;
    }
}
