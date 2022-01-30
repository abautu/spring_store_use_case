package com.example.foodvendors.batch;

import com.example.foodvendors.dtos.OfferDTO;

import lombok.Data;

@Data
public class OfferLineItem extends OfferDTO {
  String vendorName;
}
