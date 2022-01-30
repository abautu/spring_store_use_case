package com.example.foodvendors.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.example.foodvendors.entities.Offer;
import com.example.foodvendors.entities.Vendor;
import com.example.foodvendors.repositories.OfferRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OfferServiceTests {
  @InjectMocks
  OfferService offerService;

  @Mock
  OfferRepository offerRepository;

  @Test
  void givenSomeOffers_whenFindAll_thenReturnsThem() {
    // given
    Vendor vendor = new Vendor(1, "vendor", "description");
    List<Offer> offers = new ArrayList<>();
    offers.add(new Offer(1, "offer1", "description", vendor, 1));
    offers.add(new Offer(2, "offer2", "description", vendor, 2));
    offers.add(new Offer(3, "offer3", "description", vendor, 3));
    Mockito.when(offerRepository.findAll()).thenReturn(offers);
    // when
    List<Offer> offers2 = offerService.findAll();
    // then
    assertEquals(offers.size(), offers2.size());
    Mockito.verify(offerRepository).findAll();
    Mockito.verifyNoMoreInteractions(offerRepository);
  }

  @Test
  void givenSomeOffer_whenFindByName_thenReturnsThem() {
    // given
    Vendor vendor = new Vendor(1, "vendor", "description");
    List<Offer> offers = new ArrayList<>();
    Offer offer = new Offer(1, "offer1", "description", vendor, 1);
    offers.add(offer);
    Mockito.when(offerRepository.findByNameContains(offer.getName())).thenReturn(offers);
    Mockito.when(offerRepository.findByNameContains("noone")).thenReturn(new ArrayList<Offer>());
    // when
    List<Offer> offers2 = offerService.findByName(offer.getName());
    List<Offer> offers3 = offerService.findByName("noone");
    // then
    assertEquals(1, offers2.size());
    assertEquals(offers, offers2);
    assertEquals(0, offers3.size());
  }

  @Test
  void givenSomeOffer_whenSave_thenSaves() {
    // given
    Vendor vendor = new Vendor(1, "vendor1", "description");
    Offer offer = new Offer(1, "offer1", "description", vendor, 1);
    Mockito.when(offerRepository.save(offer)).thenReturn(offer);
    // when
    Offer offer2 = offerService.save(offer);
    // then
    assertEquals(offer, offer2);
    Mockito.verify(offerRepository).save(offer);
  }
}
