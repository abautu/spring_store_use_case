package com.example.bankaccounts.configs;

import com.example.bankaccounts.dtos.TransactionDTO;
import com.example.bankaccounts.entities.Transaction;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class TransactionToTransactionDtoConverter
  implements Converter<Transaction,TransactionDTO> {

  @Override
  public TransactionDTO convert(Transaction source) {
    TransactionDTO target = new TransactionDTO();
    BeanUtils.copyProperties(source, target);
    target.setSourceId(source.getSource().getId());
    target.setDestinationId(source.getDestination().getId());
    return target;
  }
}
