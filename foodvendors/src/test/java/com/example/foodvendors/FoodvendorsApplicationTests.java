package com.example.foodvendors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.foodvendors.controllers.OfferController;
import com.example.foodvendors.controllers.VendorController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FoodvendorsApplicationTests {
  @Autowired
  private OfferController offerController;
  @Autowired
  private VendorController vendorController;

	@Test
	void contextLoads() {
    assertNotNull(offerController);
    assertNotNull(vendorController);
	}

}
