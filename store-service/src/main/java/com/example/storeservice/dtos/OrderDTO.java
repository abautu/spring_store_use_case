package com.example.storeservice.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class OrderDTO {
  private int id;

  @Positive
  private int offer;

  @Positive
  private int buyer;

  private float price;

  @Positive
  private int quantity;

  @PastOrPresent
  LocalDateTime createdTime;
}
