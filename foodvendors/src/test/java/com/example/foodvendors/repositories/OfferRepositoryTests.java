package com.example.foodvendors.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.example.foodvendors.entities.Offer;
import com.example.foodvendors.entities.Vendor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class OfferRepositoryTests {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private VendorRepository vendorRepository;

  @Autowired
  private OfferRepository offerRepository;

  @Test
  void whenFindByNameContainsExisting_thenFind()
  {
    String name = "Vendor";
    Vendor vendor = new Vendor(0, name, name);
    vendorRepository.save(vendor);
    Offer offer1 = new Offer(0, "Offer good", "Description", vendor, 2);
    offerRepository.save(offer1);

    Offer offer2 = new Offer(0, "Offer bad", "Description", vendor, 5);
    offerRepository.save(offer2);
    entityManager.flush();

    List<Offer> offers = offerRepository.findAll();
    assertEquals(2, offers.size());

    offers = offerRepository.findByNameContains("good");
    assertEquals(1, offers.size());
    assertEquals(offer1, offers.get(0));

    offers = offerRepository.findByNameContains("so-and-so");
    assertEquals(0, offers.size());
  }
}
