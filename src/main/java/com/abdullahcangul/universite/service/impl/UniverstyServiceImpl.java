package com.abdullahcangul.universite.service.impl;

import com.abdullahcangul.universite.entity.University;
import com.abdullahcangul.universite.repository.UniversityRepository;
import com.abdullahcangul.universite.service.UniversityService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UniverstyServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniverstyServiceImpl(UniversityRepository universityRepository){
        this.universityRepository=universityRepository;
    }


    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    @Override
    public University getUniversity(int id) {
        return universityRepository.getById(id);
    }

    @Override
    public University save(University university) {
        return universityRepository.save(university);
    }


    @Override
    public  List<University> getAllUniversity() {
        final String uri = "https://gitlab.com/kodiasoft/intern/2019/snippets/1859421/raw";
        RestTemplate restTemplate = new RestTemplate();


        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        ResponseEntity<List<University>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<University>>(){});
        List<University> universities = response.getBody();

        return universities;
    }


}

