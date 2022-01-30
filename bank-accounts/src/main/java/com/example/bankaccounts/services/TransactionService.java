package com.example.bankaccounts.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.bankaccounts.entities.BankAccount;
import com.example.bankaccounts.entities.Transaction;
import com.example.bankaccounts.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private BankAccountService bankAccountService;

  public List<Transaction> findAll() {
    return transactionRepository.findAll();
  }

  public Transaction findById(int id) {
    return transactionRepository.findById(id).orElseThrow();
  }

  public List<Transaction> findStatement(BankAccount owner) {
    return transactionRepository.findBySourceEqualsOrDestinationEqualsOrderByTimestamp(owner, owner);
  }

  public Transaction create(Transaction transaction) {
    BankAccount source = transaction.getSource();
    BankAccount target = transaction.getDestination();
    float amount = transaction.getAmount();
    if (source.getBalance() < amount) {
      throw new RuntimeException("Insufficient funds");
    }
    source.updateBalance(-amount);
    bankAccountService.update(source);

    target.updateBalance(amount);
    bankAccountService.update(target);
    transaction.setId(0);
    transaction.setTimestamp(LocalDateTime.now());
    return transactionRepository.save(transaction);
  }
}
