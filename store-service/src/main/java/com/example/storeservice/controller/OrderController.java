package com.example.storeservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.storeservice.dtos.OfferDTO;
import com.example.storeservice.dtos.OrderDTO;
import com.example.storeservice.dtos.TransactionDTO;
import com.example.storeservice.services.BankService;
import com.example.storeservice.services.OrderService;
import com.example.storeservice.services.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
  @Autowired
  private VendorService vendorService;
  @Autowired
  private BankService bankService;
  @Autowired
  private OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderDTO> purchase(@Valid @RequestBody OrderDTO order) {
    OfferDTO offer = vendorService.getOfferById(order.getOffer());
    order.setPrice(offer.getPrice());
    float cost = order.getQuantity() * order.getPrice();

    TransactionDTO transaction = new TransactionDTO();
    transaction.setAmount(cost);
    transaction.setDestinationId(offer.getVendor());
    transaction.setSourceId(order.getBuyer());
    transaction.setDescription(String.format("Sale of %d items of order %s", order.getQuantity(), offer.getName()));
    bankService.create(transaction);

    order = orderService.create(order);
    return ResponseEntity.ok().body(order);
  }

  @GetMapping("/{buyerId}")
  public List<OrderDTO> readAllByOwner(@PathVariable int buyerId) {
    return orderService.findByBuyerId(buyerId);
  }

  @GetMapping
  public List<OrderDTO> readAll() {
    return orderService.findAll();
  }
}
