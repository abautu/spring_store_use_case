package com.example.foodvendors.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class OfferReader extends FlatFileItemReader<OfferLineItem> {
  public OfferReader(@Value("${offers.file}") Resource resource) {
    setResource(resource);

    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(",");
		lineTokenizer.setNames("id", "name", "vendorName", "description", "price" );

    BeanWrapperFieldSetMapper<OfferLineItem> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(OfferLineItem.class);

		DefaultLineMapper<OfferLineItem> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

    setLineMapper(defaultLineMapper);
  }
}
