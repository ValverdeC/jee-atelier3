package com.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Card {
	
	// Id auto-généré
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "description")
	private String description;
	
	@NotNull
	@Column(name = "imgUrl")
	private String imgUrl;
	
	@NotNull
	@Column(name = "family")
	private String family;
	
	@NotNull
	@Column(name = "affinity")
	private String affinity;
	
	@NotNull
	@Column(name = "ownerId")
	private int ownerId;
	
	@NotNull
	@Column(name = "hp")
	private int hp;	
	
	@NotNull
	@Column(name = "price")
	private int price;
	
	@NotNull
	@Column(name = "energy")
	private int energy;
	
	@NotNull
	@Column(name = "attack")
	private int attack;
	
	@NotNull
	@Column(name = "defence")
	private int defence;
	
	/**
	 * Lien entre une carte et un utilisateur
	 */
	

	/************************/
	/*** Getter & Setters ***/
	/************************/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getAffinity() {
		return affinity;
	}
	public void setAffinity(String affinity) {
		this.affinity = affinity;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public int getPrice() {
		return this.price;
	}
	public void setPrice(int price) {
		this.price=price;
	}
	public int getOwnerId() {
		return this.ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
}
