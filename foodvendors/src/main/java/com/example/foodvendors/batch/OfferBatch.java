package com.example.foodvendors.batch;

import com.example.foodvendors.entities.Offer;

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
public class OfferBatch {
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

  @Autowired
  OfferReader reader;

	@Autowired
	OfferProcessor processor;

	@Autowired
	OfferWriter writer;

	public Job getJob() {
		Step step = stepBuilderFactory.get("step-1")
      .<OfferLineItem, Offer> chunk(2)
      .reader(reader)
      .processor(processor)
      .writer(writer)
      .build();

		return jobBuilderFactory.get("offer-job")
      .incrementer(new RunIdIncrementer())
      .listener(this)
      .start(step)
      .build();
	}
}
