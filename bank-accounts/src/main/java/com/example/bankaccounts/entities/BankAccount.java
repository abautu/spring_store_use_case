package com.example.bankaccounts.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BankAccount {
  @Id
  @GeneratedValue
  private int id;

  private int ownerId;

  private float balance;

  private LocalDate openDate;

  public float updateBalance(float amount) {
    balance += amount;
    return balance;
  }
}
