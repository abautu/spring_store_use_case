package com.example.storeservice.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class TransactionDTO {
  private int id;

  int sourceId;

  int destinationId;

  LocalDateTime timestamp;

  @Min(0)
  float amount;

  String description;
}
