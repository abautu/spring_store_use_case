package com.example.foodvendors.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
  @Id
  @GeneratedValue
  private int id;

  @Column(nullable = false, unique = true)
  @NotBlank(message = "{name-required}")
  private String name;

  private String description;

}
