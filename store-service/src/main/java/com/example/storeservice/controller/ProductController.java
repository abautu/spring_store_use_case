package com.example.storeservice.controller;

import java.util.List;

import com.example.storeservice.dtos.OfferDTO;
import com.example.storeservice.services.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
public class ProductController {
  @Autowired
  private VendorService vendorService;

  @GetMapping
  public List<OfferDTO> readAll() {
    return vendorService.getOffers();
  }

  @GetMapping("/{search}")
  public List<OfferDTO> search(@PathVariable String search) {
    return vendorService.getOffers(search);
  }
}
