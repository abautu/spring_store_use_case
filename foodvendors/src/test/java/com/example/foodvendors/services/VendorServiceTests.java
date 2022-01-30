package com.example.foodvendors.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.foodvendors.entities.Vendor;
import com.example.foodvendors.repositories.VendorRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VendorServiceTests {
  @InjectMocks
  VendorService vendorService;

  @Mock
  VendorRepository vendorRepository;

  @Test
  void givenSomeVendors_whenFindAll_thenReturnsThem() {
    // given
    List<Vendor> vendors = new ArrayList<>();
    vendors.add(new Vendor(1, "vendor1", "description"));
    vendors.add(new Vendor(2, "vendor2", "description"));
    vendors.add(new Vendor(3, "vendor3", "description"));
    Mockito.when(vendorRepository.findAll()).thenReturn(vendors);
    // when
    List<Vendor> vendors2 = vendorService.findAll();
    // then
    assertEquals(vendors.size(), vendors2.size());
    Mockito.verify(vendorRepository).findAll();
    Mockito.verifyNoMoreInteractions(vendorRepository);
  }

  @Test
  void givenSomeVendor_whenFindByName_thenReturnsThem() {
    // given
    Vendor vendor = new Vendor(0, "vendor1", "description");
    Mockito.when(vendorRepository.findByName(vendor.getName())).thenReturn(vendor);
    Mockito.when(vendorRepository.findByName("noone")).thenReturn(null);
    // when
    Vendor vendor2 = vendorService.findByName(vendor.getName());
    Vendor vendor3 = vendorService.findByName("noone");
    // then
    assertEquals(vendor, vendor2);
    assertNull(vendor3);
  }

  @Test
  void givenSomeVendor_whenFindById_thenReturnsThem() {
    // given
    Vendor vendor = new Vendor(1, "vendor1", "description");
    Mockito.when(vendorRepository.findById(vendor.getId())).thenReturn(Optional.of(vendor));
    Mockito.when(vendorRepository.findById(0)).thenReturn(Optional.empty());
    // when
    Vendor vendor2 = vendorService.findById(vendor.getId());
    Vendor vendor3 = vendorService.findById(0);
    // then
    assertEquals(vendor, vendor2);
    assertNull(vendor3);
  }

  @Test
  void givenSomeVendor_whenSave_thenSaves() {
    // given
    Vendor vendor = new Vendor(0, "vendor1", "description");
    Mockito.when(vendorRepository.save(vendor)).thenReturn(vendor);
    // when
    Vendor vendor2 = vendorService.save(vendor);
    // then
    assertEquals(vendor, vendor2);
    Mockito.verify(vendorRepository).save(vendor);
  }
}
