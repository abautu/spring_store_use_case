package com.example.foodvendors.batch;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.example.foodvendors.dtos.OfferEntityDTOConverter;
import com.example.foodvendors.entities.Offer;
import com.example.foodvendors.entities.Vendor;
import com.example.foodvendors.services.VendorService;

import org.jboss.logging.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferProcessor
  implements ItemProcessor<OfferLineItem, Offer> {

  private static final Logger LOGGER = Logger.getLogger(OfferProcessor.class);

  @Autowired
  private Validator validator;

  private OfferEntityDTOConverter convertor = new OfferEntityDTOConverter();

  @Autowired
  private VendorService vendorService;

  @Override
  public Offer process(OfferLineItem item) throws Exception {
    Vendor vendor = vendorService.findByName(item.vendorName);
    if (vendor != null) {
      item.setVendor(vendor.getId());
    }
    Offer offer = convertor.dtoToEntity(item);
    Set<ConstraintViolation<Offer>> errors = validator.validate(offer);
    if (!errors.isEmpty()) {
      LOGGER.info("Ignoring offer (invalid)" + item + errors);
      return null;
    }
    return offer;
  }

}
