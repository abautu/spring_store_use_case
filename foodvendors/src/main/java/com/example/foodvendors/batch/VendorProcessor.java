package com.example.foodvendors.batch;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.example.foodvendors.dtos.VendorEntityDTOConverter;
import com.example.foodvendors.entities.Vendor;

import org.jboss.logging.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorProcessor
  implements ItemProcessor<VendorLineItem, Vendor> {

  private static final Logger LOGGER = Logger.getLogger(VendorProcessor.class);

  @Autowired
  private Validator validator;

  private VendorEntityDTOConverter convertor = new VendorEntityDTOConverter();

  @Override
  public Vendor process(VendorLineItem item) throws Exception {
    Vendor vendor = convertor.dtoToEntity(item);
    Set<ConstraintViolation<Vendor>> errors = validator.validate(vendor);
    if (!errors.isEmpty()) {
      LOGGER.info("Ignoring vendor (invalid) " + item + errors);
      return null;
    }
    return vendor;
  }
}
