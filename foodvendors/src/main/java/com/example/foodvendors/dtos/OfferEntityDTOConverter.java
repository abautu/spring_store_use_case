package com.example.foodvendors.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.example.foodvendors.entities.Offer;
import com.example.foodvendors.services.VendorService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class OfferEntityDTOConverter
{
  @Autowired
  private VendorService vendorService;

  public OfferDTO entityToDto(Offer source) {
    OfferDTO target = new OfferDTO();
    BeanUtils.copyProperties(source, target);
    if (source.getVendor() != null) {
      target.setVendor(source.getVendor().getId());
    }
    return target;
  }

  public Offer dtoToEntity(OfferDTO source) {
    Offer target = new Offer();
    BeanUtils.copyProperties(source, target);
    target.setVendor(vendorService.findById(source.getVendor()));
    return target;
  }

  public List<OfferDTO> entitiesToDtos(List<Offer> source) {
    return source.stream().map(this::entityToDto).collect(Collectors.toList());
  }

  public List<Offer> dtosToEntities(List<OfferDTO> source) {
    return source.stream().map(this::dtoToEntity).collect(Collectors.toList());
  }
}
