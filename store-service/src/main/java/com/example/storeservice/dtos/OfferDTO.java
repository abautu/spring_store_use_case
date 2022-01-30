package com.example.storeservice.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfferDTO {
  private int id;

  @NotBlank
  private String name;

  private String description;

  @Positive
  private int vendor;

  @Positive
  float price;
}
