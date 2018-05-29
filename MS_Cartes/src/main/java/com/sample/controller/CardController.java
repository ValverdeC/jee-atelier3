package com.sample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Card;
import com.sample.service.CardService;

@RestController
public class CardController {
	
	@Autowired
	private CardService service;
	
	/**
	 * Récupération de toutes les cartes en base
	 * 
	 * @return List<Card>
	 */
	@RequestMapping("/cards")
	public List<Card> getAllCards() {
		return this.service.getAll();
	}
	
	/**
	 * Modification d'une carte
	 * 
	 * @param card
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT, value="card/{id}")
	public Card updateCard(@RequestBody Card card, @PathVariable int id) {
		return this.service.update(card, id);
	}
	
	/**
	 * Ajout d'une carte pour un utilisateur
	 * 
	 * @param userId
	 * @param card
	 * @return
	 */
	@PostMapping("/card")
    public Card addCard(@Valid @RequestBody Card card) {
        return this.service.add(card);
    }
	
	/**
	 * Suppresion d'une carte
	 * 
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="card/{id}")
	public void deleteCard(@PathVariable int id) {
		this.service.delete(id);
	}
	
	/**
	 * Récupération d'une carte par son id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("card/{id}")
	public Card getCardById(@PathVariable int id) {
		return this.service.getById(id);
	}
	
	@RequestMapping("card/name/{name}")
	public List<Card> getCardByName(@PathVariable  (value = "name") String name) {
		return this.service.getCardByName(name);
	}
	
	@RequestMapping("card/user/{ownerId}")
	public List<Card> getCardByOwnerId(@PathVariable int ownerId) {
		return this.service.getCardByOwnerId(ownerId);
	}
	
	@RequestMapping("card/available")
	public List<Card> getCardAvailable() {
		return this.service.getCardAvailable();
	}
	
	@RequestMapping("card/buy/{cardId}/{userId}")
	public void buyCard(@PathVariable (value = "userId") int userId,
						@PathVariable (value = "cardId") int cardId) {
		
		this.service.buyCard(userId, cardId);
	}
	
	@RequestMapping("card/sell/{cardId}/{userId}")
	public void sell(@PathVariable (value = "userId") int userId,
						@PathVariable (value = "cardId") int cardId) {
		
		this.service.sellCard(userId, cardId);
	}	

}
