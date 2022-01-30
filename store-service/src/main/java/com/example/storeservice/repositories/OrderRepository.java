package com.example.storeservice.repositories;

import java.util.List;

import com.example.storeservice.entities.Order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
  extends CrudRepository<Order,Integer>{
  List<Order> findAll();
  List<Order> findByBuyer(int buyer);
}
