package com.example.foodvendors.dtos;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class VendorDTO {
  private int id;

  @NotBlank(message = "{name-required}")
  private String name;

  private String description;
}
