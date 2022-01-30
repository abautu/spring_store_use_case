package com.example.foodvendors.batch;

import java.util.List;

import com.example.foodvendors.entities.Offer;
import com.example.foodvendors.services.OfferService;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OfferWriter implements ItemWriter<Offer> {
  @Autowired
  private OfferService offerService;

  @Override
  @Transactional
  public void write(List<? extends Offer> items) throws Exception {
    items.stream().forEach(offerService::save);
  }
}
