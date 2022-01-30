package com.example.storeservice.services;

import java.util.List;

import com.example.storeservice.dtos.TransactionDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="bank-service",qualifiers = "bankService")
public interface BankService {
  @PostMapping("/bank/transactions")
  public TransactionDTO create(TransactionDTO transactionDTO);

  @GetMapping("/bank/by-owner/{id}")
  public List<TransactionDTO> readStatement(@PathVariable int id);
}
