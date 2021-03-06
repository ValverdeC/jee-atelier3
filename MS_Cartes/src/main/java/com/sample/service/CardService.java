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
	public Card add(Card card) {
		return this.repository.save(card);
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
	
	public List<Card> getCardByName(String cardName) {
		return this.repository.findByName(cardName);
	}
	
	/**
	 * 
	 * @param idUser
	 * @param idCard
	 * @return
	 */
	public Card buyCard(int idUser, int idCard) {
		
		//todo: check auth
		//todo: get wallet + check si assez de  €€€€$$$$££££
		Card card = this.repository.findOne(idCard);
		
		if(card==null) {
			throw new ResourceNotFoundException("Carte numéro: "+idCard+" inexistante");	
		}
		if(card.getOwnerId()!=0){
			throw new ResourceNotFoundException("Carte numéro: "+idCard+" inexistante");
		}else {
			card.setOwnerId(idUser);
			return this.repository.save(card);
			//todo: update wallet
		}
	}
	
	public Card sellCard(int idOwner, int idCard) {
		
		//todo: check auth
		Card card = this.repository.findOne(idCard);
		
		if(card==null) {
			throw new ResourceNotFoundException("Carte numéro: "+idCard+" inexistante");	
		}
		if(card.getOwnerId()!=idOwner){
			throw new ResourceNotFoundException("Carte numéro: "+idCard+" inexistante");
		}else {
			card.setOwnerId(0);
			//todo: ajouter l'appel pour ajouter l'argent dans le portefeuille de l'ancien propriétaire
			return this.repository.save(card);
		}
	}
}
