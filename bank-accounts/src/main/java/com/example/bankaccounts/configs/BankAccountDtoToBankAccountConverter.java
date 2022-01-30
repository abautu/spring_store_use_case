package com.example.bankaccounts.configs;

import com.example.bankaccounts.dtos.BankAccountDTO;
import com.example.bankaccounts.entities.BankAccount;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class BankAccountDtoToBankAccountConverter
  implements Converter<BankAccountDTO, BankAccount> {
    @Override
    public BankAccount convert(BankAccountDTO source) {
      BankAccount target = new BankAccount();
      BeanUtils.copyProperties(source, target, "openDate");
      return target;
    }
}
