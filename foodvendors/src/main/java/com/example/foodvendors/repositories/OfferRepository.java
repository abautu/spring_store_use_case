package com.example.foodvendors.repositories;

import java.util.List;
import com.example.foodvendors.entities.Offer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {
  List<Offer> findAll();

  List<Offer> findByNameContains(String keyword);
}
