package com.example.storeservice.services;

import java.util.List;

import com.example.storeservice.dtos.OfferDTO;
import com.example.storeservice.dtos.VendorDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="vendor-service",qualifiers = "vendorService")
public interface VendorService {
  @GetMapping("/vendors/vendors")
  public List<VendorDTO> getVendors();

  @GetMapping("/vendors/offers")
  public List<OfferDTO> getOffers();

  @GetMapping("/vendors/offers")
  public List<OfferDTO> getOffers(@RequestParam String keyword);

  @GetMapping("/vendors/offers/{id}")
  public OfferDTO getOfferById(@PathVariable int id);
}
