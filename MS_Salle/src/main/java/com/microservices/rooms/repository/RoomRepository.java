package com.microservices.rooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.microservices.rooms.model.Room;

@NoRepositoryBean
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
