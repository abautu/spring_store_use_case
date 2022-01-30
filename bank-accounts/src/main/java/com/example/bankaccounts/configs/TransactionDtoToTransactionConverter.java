package com.example.bankaccounts.configs;

import com.example.bankaccounts.dtos.TransactionDTO;
import com.example.bankaccounts.entities.Transaction;
import com.example.bankaccounts.services.BankAccountService;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class TransactionDtoToTransactionConverter
  implements Converter<TransactionDTO,Transaction> {

  private BankAccountService bankAccountService;

  public TransactionDtoToTransactionConverter(BankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  @Override
  public Transaction convert(TransactionDTO source) {
    Transaction target = new Transaction();
    BeanUtils.copyProperties(source, target, "timestamp");
    target.setDestination(bankAccountService.findById(source.getDestinationId()));
    target.setSource(bankAccountService.findById(source.getSourceId()));
    return target;
  }
}
