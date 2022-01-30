package com.example.bankaccounts.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.bankaccounts.dtos.BankAccountDTO;
import com.example.bankaccounts.entities.BankAccount;
import com.example.bankaccounts.services.BankAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
  @Autowired
  private BankAccountService bankAccountService;

  @Autowired
  private ConversionService conversionService;

  @GetMapping
  public List<BankAccountDTO> readAll() {
    return bankAccountService
        .findAll()
        .stream()
        .map(a -> conversionService.convert(a, BankAccountDTO.class))
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public BankAccountDTO readByID(@PathVariable int id) {
    return conversionService.convert(bankAccountService.findById(id), BankAccountDTO.class);
  }

  @PostMapping
  public BankAccountDTO create(@RequestBody @Valid BankAccountDTO bankAccountDTO) {
    BankAccount bankAccount = conversionService.convert(bankAccountDTO, BankAccount.class);
    bankAccount = bankAccountService.create(bankAccount);
    bankAccountDTO = conversionService.convert(bankAccount, BankAccountDTO.class);
    return bankAccountDTO;
  }

  @PutMapping("/{id}")
  public BankAccountDTO update(@PathVariable int id, @RequestBody @Valid BankAccountDTO bankAccountDTO) {
    BankAccount bankAccount = conversionService.convert(bankAccountDTO, BankAccount.class);
    bankAccount.setId(id);
    bankAccount = bankAccountService.update(bankAccount);
    bankAccountDTO = conversionService.convert(bankAccount, BankAccountDTO.class);
    return bankAccountDTO;
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) {
    bankAccountService.delete(id);
  }
}
