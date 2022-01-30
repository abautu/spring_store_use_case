package com.example.foodvendors.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
  @Id
  @GeneratedValue
  private int id;

  @Column(nullable = false)
  @NotBlank(message = "{name-required}")
  private String name;

  private String description;

  @ManyToOne
  @JoinColumn(nullable = false)
  @NotNull(message = "{vendor-required}")
  private Vendor vendor;

  @Column(precision = 2)
  @Positive(message = "{price-positive}")
  float price;
}
