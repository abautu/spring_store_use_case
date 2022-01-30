package com.example.foodvendors;

import com.example.foodvendors.batch.OfferBatch;
import com.example.foodvendors.batch.VendorBatch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableEurekaClient
public class FoodvendorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodvendorsApplication.class, args);
	}

  @Bean
  @Profile("!test")
  ApplicationRunner importData(JobLauncher jobLauncher, VendorBatch vendorBatch, OfferBatch offerBatch) {
    return applicationArguments -> {

      jobLauncher.run(vendorBatch.getJob(), new JobParameters());
      jobLauncher.run(offerBatch.getJob(), new JobParameters());
    };
  }
}
