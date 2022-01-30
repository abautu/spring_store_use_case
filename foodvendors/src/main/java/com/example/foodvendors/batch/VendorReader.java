package com.example.foodvendors.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class VendorReader extends FlatFileItemReader<VendorLineItem> {
  public VendorReader(@Value("${vendors.file}") Resource resource) {
    setResource(resource);

    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(",");
		lineTokenizer.setNames("id", "name", "description" );

    BeanWrapperFieldSetMapper<VendorLineItem> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(VendorLineItem.class);

		DefaultLineMapper<VendorLineItem> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

    setLineMapper(defaultLineMapper);
  }
}
