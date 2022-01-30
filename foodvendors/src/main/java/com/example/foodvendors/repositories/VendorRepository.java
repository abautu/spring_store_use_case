package com.example.foodvendors.repositories;

import java.util.List;

import com.example.foodvendors.entities.Vendor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, Integer> {
  List<Vendor> findAll();

  Vendor findByName(String vendorName);
}
