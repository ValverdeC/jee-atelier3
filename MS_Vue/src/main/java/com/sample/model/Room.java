package com.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Room {
	
	public Room(int idJoueur1, int idCarteJoueur1, int nbTour, String name, int bet) {
		this.estLancee = false;
		this.idJoueur1 = idJoueur1;
		this.idCarteJoueur1 = idCarteJoueur1;
		this.nbTour = nbTour;
		this.name = name;
		this.bet = bet;
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Column(name = "estLancee")
	private Boolean estLancee;
	
	@NotNull
	@Column(name = "idJoueur1")
	private int idJoueur1;
	
	@Column(name = "idJoueur2")
	private int idJoueur2 = -1;
	
	@NotNull
	@Column(name = "idCarteJoueur1")
	private int idCarteJoueur1;
	
	@Column(name = "idCarteJoueur2")
	private int idCarteJoueur2 = -1;
	
	@NotNull
	@Column(name = "nbTour")
	private int nbTour;
	
	@Column(name = "nbTourJoueur1")
	private int nbTourJoueur1 = 0;
	
	@Column(name = "nbTourJoueur2")
	private int nbTourJoueur2 = 0;
	
	@Column(name = "hpCarte1")
	private int hpCarte1 = -1;
	
	@Column(name = "hpCarte2")
	private int hpCarte2 = -1;
	
	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "bet")
	private int bet;
	
	
	/************************/
	/*** Getter & Setters ***/
	/************************/
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEstLancee() {
		return estLancee;
	}
	public void setEstLancee(boolean estLancee) {
		this.estLancee = estLancee;
	}
	public int getIdJoueur1() {
		return idJoueur1;
	}
	public void setIdJoueur1(int idJoueur1) {
		this.idJoueur1 = idJoueur1;
	}
	public int getIdJoueur2() {
		return idJoueur2;
	}
	public void setIdJoueur2(int idJoueur2) {
		this.idJoueur2 = idJoueur2;
	}
	public int getIdCarteJoueur1() {
		return idCarteJoueur1;
	}
	public void setIdCarteJoueur1(int idCarteJoueur1) {
		this.idCarteJoueur1 = idCarteJoueur1;
	}
	public int getIdCarteJoueur2() {
		return idCarteJoueur2;
	}
	public void setIdCarteJoueur2(int idCarteJoueur2) {
		this.idCarteJoueur2 = idCarteJoueur2;
	}
	public int getNbTour() {
		return nbTour;
	}
	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}
	public int getNbTourJoueur1() {
		return nbTourJoueur1;
	}
	public void setNbTourJoueur1(int nbTourJoueur1) {
		this.nbTourJoueur1 = nbTourJoueur1;
	}
	public int getNbTourJoueur2() {
		return nbTourJoueur2;
	}
	public void setNbTourJoueur2(int nbTourJoueur2) {
		this.nbTourJoueur2 = nbTourJoueur2;
	}
	public int getHpCarte1() {
		return hpCarte1;
	}
	public void setHpCarte1(int hpCarte1) {
		this.hpCarte1 = hpCarte1;
	}
	public int getHpCarte2() {
		return hpCarte2;
	}
	public void setHpCarte2(int hpCarte2) {
		this.hpCarte2 = hpCarte2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public void incrementNbTourJoueur1() {
		this.nbTourJoueur1++;
	}
	public void incrementNbTourJoueur2() {
		this.nbTourJoueur2++;
	}
}
