package com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.jpa.exception.ResourceNotFoundException;
import com.sample.model.Card;
import com.sample.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;
	
	/**
	 * Récupération d'une card par son id
	 * 
	 * @param id
	 * @return Card
	 */
	public Card getById(int id) {
		return this.repository.findOne(id);
	}
	
	/**
	 * Récupération de toutes les card
	 * 
	 * @return List<Card>
	 */
	public List<Card> getAll() {
		List<Card> cards = new ArrayList<>();
		this.repository.findAll()
			.forEach(cards::add);
		return cards;
	}
	
	/**
	 * Ajout d'une carte pour un utilisateur
	 * 
	 * @param card
	 * @param id
	 * @return Card
	 */
	public Card add(Card card, int id) {
		 
		//Todo: ajouter la vérification de l'existance du user
		if (true) {
			 card.setOwnerId(id);
			 return this.repository.save(card);
		 } else {
			 throw new ResourceNotFoundException("UserId " + id + " not found");
		 }
	}
	
	/**
	 * Modification d'une card par son id
	 * 
	 * @param card
	 * @param id
	 * @return Card
	 */
	public Card update(Card card, int id) {
		return this.repository.save(card);
	}

	/**
	 * Suppresion d'une carte par son id
	 * 
	 * @param id
	 */
	public void delete(int id) {
		this.repository.delete(id);
	}
	
	/**
	 * Récupération d'une carte par l'ID de son utilisateur
	 * 
	 * @param ownerId
	 */
	public List<Card> getCardByOwnerId(int ownerId) {
		return this.repository.findByOwnerId(ownerId);
	}
	
	/**
	 * Récupération d'une carte par l'ID de son utilisateur
	 * 
	 * @param ownerId
	 */
	public List<Card> getCardAvailable() {
		return this.repository.findByOwnerId(0);
	}
}
