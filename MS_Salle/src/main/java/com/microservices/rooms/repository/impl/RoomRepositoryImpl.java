package com.microservices.rooms.repository.impl;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.rooms.model.Room;
import com.microservices.rooms.repository.RoomRepository;

@Repository("roomRepository")
public class RoomRepositoryImpl extends SimpleJpaRepository<Room, Integer> implements RoomRepository {

	@SuppressWarnings("unused")
	private EntityManager em;

	public RoomRepositoryImpl(EntityManager em) {
		super(Room.class, em);
		this.em = em;
	}
}
