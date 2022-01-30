package com.example.foodvendors.controllers;

import java.util.List;

import com.example.foodvendors.dtos.VendorDTO;
import com.example.foodvendors.dtos.VendorEntityDTOConverter;
import com.example.foodvendors.services.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendors")
public class VendorController {
  @Autowired
  private VendorService vendorService;

  private VendorEntityDTOConverter vendorEntityDTOConvertor = new VendorEntityDTOConverter();

  @GetMapping("{id}")
  public ResponseEntity<VendorDTO> readAll(@PathVariable int id) {
    return ResponseEntity.ok().body(
      vendorEntityDTOConvertor.entityToDto(vendorService.findById(id))
    );
  }

  @GetMapping
  public ResponseEntity<List<VendorDTO>> readAll() {
    return ResponseEntity
      .ok()
      .body(
        vendorEntityDTOConvertor.entitiesToDtos(vendorService.findAll())
      );
  }
}
