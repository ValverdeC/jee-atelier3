package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.sample.model.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
	List<Card> findByOwnerIdIsNull();
	List<Card> findByOwnerId(int ownerId);
	List<Card> findByOwnerIdEquals(int ownerId);
	List<Card> findByName(String cardName);
}
