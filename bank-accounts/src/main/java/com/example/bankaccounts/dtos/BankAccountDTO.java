package com.example.bankaccounts.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;

@Data
public class BankAccountDTO {
  private int id;

  @Min(1)
  private int ownerId;

  private float balance;

  @PastOrPresent
  private LocalDate openDate;
}
