package com.example.foodvendors.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.example.foodvendors.entities.Vendor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class VendorRepositoryTests {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private VendorRepository vendorRepository;

  @Test
  void whenFindByNameExisting_thenFind()
  {
    String name = "Vendor";
    Vendor vendor = new Vendor(0, name, name);
    vendorRepository.save(vendor);
    entityManager.flush();

    Vendor vendor2 = vendorRepository.findByName(name);

    assertEquals(vendor, vendor2);
  }

  @Test
  void whenFindByNameMissing_thenNull()
  {
    String name = "Vendor";
    Vendor vendor = new Vendor(0, name, name);
    vendorRepository.save(vendor);
    entityManager.flush();

    Vendor vendor2 = vendorRepository.findByName("Other" + name);

    assertNull(vendor2);
  }

  @Test
  void whenCreateDuplicate_thenFail()
  {
    assertThrows(javax.persistence.PersistenceException.class, () -> {
      String name = "VendorDuplicate";
      Vendor vendor = new Vendor(0, name, name);
      vendorRepository.save(vendor);
      entityManager.flush();

      vendor = new Vendor(0, name, name);
      vendorRepository.save(vendor);
      entityManager.flush();
    });
  }
}
