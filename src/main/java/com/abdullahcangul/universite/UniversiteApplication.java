package com.abdullahcangul.universite;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;



@SpringBootApplication
public class UniversiteApplication {


	public static void main(String[] args) {
		SpringApplication.run(UniversiteApplication.class, args);
	}

	@Bean
	MappingJackson2HttpMessageConverter converter() {

		MappingJackson2HttpMessageConverter converter
				= new MappingJackson2HttpMessageConverter();
		return converter;
	}



	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}


}

