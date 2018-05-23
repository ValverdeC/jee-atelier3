package com.microservices.rooms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.rooms.services.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	private RoomService service;

}
