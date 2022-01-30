package com.example.bankaccounts.configs;

import com.example.bankaccounts.dtos.BankAccountDTO;
import com.example.bankaccounts.entities.BankAccount;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

public class BankAccountToBankAccountDtoConverter
  implements Converter<BankAccount, BankAccountDTO> {
    @Override
    public BankAccountDTO convert(BankAccount source) {
      BankAccountDTO target = new BankAccountDTO();
      BeanUtils.copyProperties(source, target);
      return target;
    }
}
