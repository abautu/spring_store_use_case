package com.example.bankaccounts.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.bankaccounts.dtos.TransactionDTO;
import com.example.bankaccounts.entities.BankAccount;
import com.example.bankaccounts.entities.Transaction;
import com.example.bankaccounts.services.BankAccountService;
import com.example.bankaccounts.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
  @Autowired
  private BankAccountService bankAccountService;
  @Autowired
  private TransactionService transactionService;
  @Autowired
  private ConversionService conversionService;

  @GetMapping
  public List<TransactionDTO> readAll() {
    return transactionService
      .findAll()
      .stream()
      .map(t -> conversionService.convert(t, TransactionDTO.class))
      .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public TransactionDTO readById(@PathVariable int id) {
    return conversionService.convert(transactionService.findById(id), TransactionDTO.class);
  }

  @GetMapping("/by-owner/{id}")
  public List<TransactionDTO> readStatement(@PathVariable int id) {
    BankAccount account = bankAccountService.findById(id);
    return transactionService
      .findStatement(account)
      .stream()
      .map(t -> conversionService.convert(t, TransactionDTO.class))
      .collect(Collectors.toList());
  }

  @PostMapping
  public TransactionDTO create(@RequestBody @Valid TransactionDTO transactionDTO) {
    Transaction transaction = conversionService.convert(transactionDTO, Transaction.class);
    transaction = transactionService.create(transaction);
    return conversionService.convert(transaction, TransactionDTO.class);
  }
}
