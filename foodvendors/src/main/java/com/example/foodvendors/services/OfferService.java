package com.example.foodvendors.services;

import java.util.List;

import javax.validation.Valid;

import com.example.foodvendors.entities.Offer;
import com.example.foodvendors.repositories.OfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferService {
  @Autowired
  private OfferRepository offerRepository;

  public Offer save(@Valid Offer offer) {
    return offerRepository.save(offer);
  }

  public List<Offer> findAll() {
    return offerRepository.findAll();
  }

  public List<Offer> findByName(String keyword) {
    return offerRepository.findByNameContains(keyword);
  }

  public Offer findById(int id) {
    return offerRepository.findById(id).orElse(null);
  }
}
