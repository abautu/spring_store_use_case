package com.example.bankaccounts.configs;

import com.example.bankaccounts.services.BankAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Autowired
  private BankAccountService bankAccountService;

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new BankAccountDtoToBankAccountConverter());
    registry.addConverter(new BankAccountToBankAccountDtoConverter());
    registry.addConverter(new TransactionDtoToTransactionConverter(bankAccountService));
    registry.addConverter(new TransactionToTransactionDtoConverter());
    WebMvcConfigurer.super.addFormatters(registry);
  }
}
