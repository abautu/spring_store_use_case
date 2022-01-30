package com.example.foodvendors.dtos;

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

  @NotBlank(message = "{name-required}")
  private String name;

  private String description;

  @Positive(message = "{vendor-required}")
  private int vendor;

  @Positive(message = "{price-positive}")
  float price;
}
