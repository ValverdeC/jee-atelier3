package com.microservices.rooms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.exception.ResourceNotFoundException;
import com.microservices.rooms.model.Room;
import com.microservices.rooms.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository repository;
	
	public Room createRoom(Room room) {
		return this.repository.save(room);
	}
	
	public void deleteRoom(int id) {
		this.repository.delete(id);
	}
	
	public List<Room> getAllRooms() {
		return this.repository.findAll();
	}

	public Room updateRoom(int id, Room room) {
		room.setId(id);
		return this.repository.save(room);
	}

	public Room joinRoom(int id, int idJoueur2, int idCarteJoueur2) {
		Room roomToJoin = this.repository.findOne(id);
		
		if (roomToJoin != null) {
			roomToJoin.setIdJoueur2(idJoueur2);
			roomToJoin.setIdCarteJoueur2(idCarteJoueur2);
			
			return this.repository.save(roomToJoin);
		} else {
			throw new ResourceNotFoundException("Room with id " + id + " not found");
		}
	}

	public Room startRoomGame(int id) {
		Room roomToStart = this.repository.findOne(id);
		
		if (roomToStart != null) {
			roomToStart.setEstLancee(true);
			
			return this.repository.save(roomToStart);
		} else {
			throw new ResourceNotFoundException("Room with id " + id + " not found");
		}
	}

	public Room hitOpponent(int id, int idJoueur) {
		Room roomOfGame = this.repository.findOne(id);
		
		if (roomOfGame != null) {
			if (roomOfGame.isEstLancee()) {
				// TODO: Appel aux autres MS pour récupérer HP de l'attaquant et décrémenter les HP dans la room
				if (idJoueur == 1) {
					roomOfGame.incrementNbTourJoueur1();
					// TODO: Décrémenter les HP du joueur 2 dans la room
				} else {
					roomOfGame.incrementNbTourJoueur2();
					// TODO: Décrémenter les HP du joueur 1 dans la room
				}
				return this.repository.save(roomOfGame);				
			}
			throw new ResourceNotFoundException("Cannot play if game is not launched");
		} else {
			throw new ResourceNotFoundException("Room with id " + id + " not found");
		}
	}

	public Room stopRoomGame(int id) {
		Room roomToStop = this.repository.findOne(id);
		
		if (roomToStop != null) {
			roomToStop.setEstLancee(false);
			
			return this.repository.save(roomToStop);
		} else {
			throw new ResourceNotFoundException("Room with id " + id + " not found");
		}
	}
}
