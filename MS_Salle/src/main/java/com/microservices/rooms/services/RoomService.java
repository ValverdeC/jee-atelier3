package com.microservices.rooms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.rooms.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository repository;
	
	
}
