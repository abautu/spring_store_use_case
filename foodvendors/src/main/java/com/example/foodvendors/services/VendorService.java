package com.example.foodvendors.services;

import java.util.List;

import javax.validation.Valid;

import com.example.foodvendors.entities.Vendor;
import com.example.foodvendors.repositories.VendorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorService {
  @Autowired
  private VendorRepository vendorRepository;

  public List<Vendor> findAll() {
    return vendorRepository.findAll();
  }

  public Vendor save(@Valid Vendor vendor) {
    return vendorRepository.save(vendor);
  }

  public Vendor findById(int id) {
    return vendorRepository.findById(id).orElse(null);
  }

  public Vendor findByName(String vendorName) {
    return vendorRepository.findByName(vendorName);
  }
}
