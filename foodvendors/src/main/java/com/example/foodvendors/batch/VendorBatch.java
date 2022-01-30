package com.example.foodvendors.batch;

import com.example.foodvendors.entities.Vendor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableBatchProcessing
public class VendorBatch {
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

  @Autowired
  VendorReader reader;

	@Autowired
	VendorProcessor processor;

	@Autowired
	VendorWriter writer;

	public Job getJob() {
		Step step = stepBuilderFactory.get("step-1")
      .<VendorLineItem, Vendor> chunk(2)
      .reader(reader)
      .processor(processor)
      .writer(writer)
      .build();

		return jobBuilderFactory.get("vendor-job")
      .incrementer(new RunIdIncrementer())
      .listener(this)
      .start(step)
      .build();
	}
}
