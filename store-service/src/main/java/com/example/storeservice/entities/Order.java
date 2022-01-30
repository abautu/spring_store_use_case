package com.example.storeservice.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name="orders")
public class Order {
  @Id
  @GeneratedValue
  private int id;

  private int offer;

  private int buyer;

  private float price;

  private int quantity;

  LocalDateTime createdTime;
}
