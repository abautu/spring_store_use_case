package com.example.bankaccounts.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transaction {
  @Id
  @GeneratedValue
  private int id;

  @ManyToOne
  BankAccount source;

  @ManyToOne
  BankAccount destination;

  LocalDateTime timestamp;

  float amount;

  String description;
}
