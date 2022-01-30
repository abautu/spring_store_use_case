package com.example.foodvendors.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.foodvendors.entities.Vendor;
import com.example.foodvendors.services.VendorService;

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
@WebMvcTest(controllers = VendorController.class)
class VendorControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private VendorService vendorService;

  @Test
  void givenSomeVendors_whenGet_returnsAll() throws Exception {
    // given
    List<Vendor> vendors = new ArrayList<>();
    vendors.add(new Vendor(1, "vendor1", "description"));
    vendors.add(new Vendor(2, "vendor2", "description"));
    vendors.add(new Vendor(3, "vendor3", "description"));
    Mockito.when(vendorService.findAll()).thenReturn(vendors);
    // when
    mockMvc.perform(MockMvcRequestBuilders.get("/vendors"))
    // then
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(vendors.size())))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(vendors.get(0).getName())));
  }

  @Test
  void givenSomeVendors_whenGetKeyword_returnsAll() throws Exception {
    // given
    List<Vendor> vendors = new ArrayList<>();
    Vendor vendor = new Vendor(1, "vendor1", "description");
    vendors.add(vendor);
    Mockito.when(vendorService.findById(vendor.getId())).thenReturn(vendor);
    // when
    mockMvc.perform(MockMvcRequestBuilders.get("/vendors/{id}", vendor.getId()))
    // then
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(vendor.getName())));
  }
}
