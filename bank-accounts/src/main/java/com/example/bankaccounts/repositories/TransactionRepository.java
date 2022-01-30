package com.example.bankaccounts.repositories;

import java.util.List;

import com.example.bankaccounts.entities.BankAccount;
import com.example.bankaccounts.entities.Transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
  extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAll();
    List<Transaction> findBySourceEqualsOrDestinationEqualsOrderByTimestamp(BankAccount source, BankAccount destination);
}
