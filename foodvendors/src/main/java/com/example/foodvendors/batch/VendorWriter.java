package com.example.foodvendors.batch;

import java.util.List;

import com.example.foodvendors.entities.Vendor;
import com.example.foodvendors.services.VendorService;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VendorWriter implements ItemWriter<Vendor> {
  @Autowired
  private VendorService vendorService;

  @Override
  @Transactional
  public void write(List<? extends Vendor> items) throws Exception {
    items.stream().forEach(vendorService::save);
  }
}
