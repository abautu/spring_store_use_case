package com.example.storeservice.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.storeservice.dtos.OrderDTO;
import com.example.storeservice.entities.Order;
import com.example.storeservice.repositories.OrderRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  @Autowired
  OrderRepository orderRepository;

  public List<OrderDTO> findAll() {
    return orderRepository
      .findAll()
      .stream()
      .map(this::entityToDto)
      .collect(Collectors.toList());
  }

  public List<OrderDTO> findByBuyerId(int id) {
    return orderRepository.findByBuyer(id)
      .stream()
      .map(this::entityToDto)
      .collect(Collectors.toList());
  }

  public OrderDTO findById(int id) {
    return entityToDto(orderRepository.findById(id).orElseThrow());
  }

  public OrderDTO create(OrderDTO orderDTO) {
    Order order = dtoToEntity(orderDTO);
    order.setCreatedTime(LocalDateTime.now());
    order.setId(0);
    order = orderRepository.save(order);
    return entityToDto(order);
  }

  protected OrderDTO entityToDto(Order source) {
    OrderDTO target = new OrderDTO();
    BeanUtils.copyProperties(source, target);
    return target;
  }

  protected Order dtoToEntity(OrderDTO source) {
    Order target = new Order();
    BeanUtils.copyProperties(source, target);
    return target;
  }

}
