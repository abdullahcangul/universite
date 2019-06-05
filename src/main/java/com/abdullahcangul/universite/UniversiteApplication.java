package com.abdullahcangul.universite;

import com.abdullahcangul.universite.service.impl.UniverstyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
}
