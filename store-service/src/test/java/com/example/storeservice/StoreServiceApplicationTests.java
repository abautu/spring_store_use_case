package com.example.storeservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreServiceApplicationTests {

	@Test
	void contextLoads() {
    int val = 3%2;
    assertEquals(1, val);
	}

}
