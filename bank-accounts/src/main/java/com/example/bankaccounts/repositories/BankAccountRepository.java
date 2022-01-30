package com.example.bankaccounts.repositories;

import java.util.List;

import com.example.bankaccounts.entities.BankAccount;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository
  extends CrudRepository<BankAccount,Integer> {
    List<BankAccount> findAll();

}
