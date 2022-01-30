package com.example.foodvendors.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.foodvendors.entities.Offer;
import com.example.foodvendors.services.OfferService;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@WebMvcTest(controllers = OfferController.class)
class OfferControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OfferService offerService;

  @Test
  void givenSomeOffers_whenGet_returnsAll() throws Exception {
    // given
    List<Offer> offers = new ArrayList<>();
    offers.add(new Offer(1, "offer1", "description", null, 1));
    offers.add(new Offer(2, "offer2", "description", null, 1));
    offers.add(new Offer(3, "offer3", "description", null, 1));
    Mockito.when(offerService.findAll()).thenReturn(offers);
    // when
    mockMvc.perform(MockMvcRequestBuilders.get("/offers"))
    // then
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(offers.size())))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(offers.get(0).getName())));
  }

  @Test
  void givenSomeOffers_whenGetKeyword_returnsAll() throws Exception {
    // given
    List<Offer> offers = new ArrayList<>();
    Offer offer = new Offer(1, "offer1", "description", null, 1);
    offers.add(offer);
    Mockito.when(offerService.findById(offer.getId())).thenReturn(offer);
    // when
    mockMvc.perform(MockMvcRequestBuilders.get("/offers/{id}", offer.getId()))
    // then
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(offer.getName())));
  }
}
