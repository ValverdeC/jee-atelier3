package com.microservices.rooms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.rooms.model.Room;
import com.microservices.rooms.services.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	private RoomService service;
	
	/**
	 * Créer une room
	 * 
	 * @param room
	 * @return Room
	 */
	@PostMapping("/game/room")
	public Room createRoom(@Valid @RequestBody Room room) {
		return this.service.createRoom(room);
	}
	
	/**
	 * Supprimer une room par son id
	 * 
	 * @param id
	 */
	@DeleteMapping("/game/room/{id}")
	public void deleteRoom(@PathVariable int id) {
		this.service.deleteRoom(id);
	}
	
	/**
	 * Récupérer toutes les rooms
	 * 
	 * @return List<Room>
	 */
	@GetMapping("/game/rooms")
	public List<Room> getAllRooms() {
		return this.service.getAllRooms();
	}
	
	/**
	 * Mettre à jour une room par son id
	 * 
	 * @param id
	 * @param room
	 * @return Room
	 */
	@PutMapping("/game/room/{id}")
	public Room updateRoom(@PathVariable int id, @Valid @RequestBody Room room) {
		return this.service.updateRoom(id, room);
	}
	
	/**
	 * Rejoindre une room
	 * 
	 * @param id
	 * @param idJoueur2
	 * @param idCarteJoueur2
	 * @return Room
	 */
	@PostMapping("/game/room/{id}/join/{idJoueur2}/{idCarteJoueur2}")
	public Room joinRoom(@PathVariable int id, @PathVariable int idJoueur2, @PathVariable int idCarteJoueur2) {
		return this.service.joinRoom(id, idJoueur2, idCarteJoueur2);
	}
	
	/**
	 * Lancer la partie d'une room
	 * 
	 * @param id
	 * @return Room
	 */
	@PostMapping("/game/room/{id}/start")
	public Room startRoomGame(@PathVariable int id) {
		return this.service.startRoomGame(id);
	}
	
	/**
	 * Frapper le joueur adverse dans une room
	 * (l'id de joueur est celui qui donne le coup)
	 * 
	 * @param id
	 * @param idJoueur
	 * @return Room
	 */
	@PostMapping("/game/room/{id}/player/{idJoueur}/hit")
	public Room hitOpponent(@PathVariable int id, @PathVariable int idJoueur) {
		return this.service.hitOpponent(id, idJoueur);
	}

	/**
	 * Arrêter la partie d'une room par son id
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("game/room/{id}/stop")
	public Room stopRoomGame(@PathVariable int id) {
		return this.service.stopRoomGame(id);
	}
}
