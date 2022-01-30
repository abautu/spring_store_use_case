package com.example.foodvendors.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.example.foodvendors.entities.Vendor;

import org.springframework.beans.BeanUtils;

public class VendorEntityDTOConverter
{
  public VendorDTO entityToDto(Vendor source) {
    VendorDTO target = new VendorDTO();
    BeanUtils.copyProperties(source, target);
    return target;
  }

  public Vendor dtoToEntity(VendorDTO source) {
    Vendor target = new Vendor();
    BeanUtils.copyProperties(source, target);
    return target;
  }

  public List<VendorDTO> entitiesToDtos(List<Vendor> source) {
    return source.stream().map(this::entityToDto).collect(Collectors.toList());
  }

  public List<Vendor> dtosToEntities(List<VendorDTO> source) {
    return source.stream().map(this::dtoToEntity).collect(Collectors.toList());
  }
}
