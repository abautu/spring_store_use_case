package com.example.bankaccounts.services;

import java.time.LocalDate;
import java.util.List;

import com.example.bankaccounts.entities.BankAccount;
import com.example.bankaccounts.repositories.BankAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
  @Autowired
  private BankAccountRepository bankAccountRepository;

  public List<BankAccount> findAll() {
    return bankAccountRepository.findAll();
  }

  public BankAccount findById(int id) {
    return bankAccountRepository.findById(id).orElseThrow();
  }

  public BankAccount create(BankAccount bankAccount) {
    bankAccount.setId(0);
    bankAccount.setOpenDate(LocalDate.now());
    return bankAccountRepository.save(bankAccount);
  }

  public BankAccount update(BankAccount bankAccount) {
    return bankAccountRepository.save(bankAccount);
  }

  public void delete(int id) {
    bankAccountRepository.deleteById(id);
  }

}
