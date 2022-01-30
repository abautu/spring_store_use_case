package com.example.foodvendors.controllers;

import java.util.List;

import com.example.foodvendors.dtos.OfferDTO;
import com.example.foodvendors.dtos.OfferEntityDTOConverter;
import com.example.foodvendors.entities.Offer;
import com.example.foodvendors.services.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
public class OfferController {
  @Autowired
  private OfferService offerService;

  private OfferEntityDTOConverter offerEntityDTOConvertor = new OfferEntityDTOConverter();

  @GetMapping
  public ResponseEntity<List<OfferDTO>> readAll(@RequestParam(required = false) String keyword) {
    List<Offer> offers = keyword == null ? offerService.findAll() : offerService.findByName(keyword);
    return ResponseEntity
      .ok()
      .body(
        offerEntityDTOConvertor.entitiesToDtos(offers)
      );
  }

  @GetMapping("/{id}")
  public ResponseEntity<OfferDTO> readByID(@PathVariable int id) {
    return ResponseEntity
      .ok()
      .body(
        offerEntityDTOConvertor.entityToDto(offerService.findById(id))
      );
  }
}
