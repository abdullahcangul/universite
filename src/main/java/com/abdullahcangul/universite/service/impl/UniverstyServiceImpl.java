package com.abdullahcangul.universite.service.impl;

import com.abdullahcangul.universite.dto.OneStudentDto;
import com.abdullahcangul.universite.dto.UniversityDetailDto;
import com.abdullahcangul.universite.dto.UniversityDto;
import com.abdullahcangul.universite.entity.University;
import com.abdullahcangul.universite.entity.errorModel.NotFound;
import com.abdullahcangul.universite.entity.errorModel.ServiceResult;
import com.abdullahcangul.universite.repository.UniversityRepository;
import com.abdullahcangul.universite.service.UniversityService;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniverstyServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    private final ModelMapper modelMapper;

    public UniverstyServiceImpl(UniversityRepository universityRepository, ModelMapper modelMapper){
        this.universityRepository=universityRepository;
        this.modelMapper = modelMapper;
    }

    //Veritabanında kayıtlı tüm üniversiteleri  dönen method blogu
    @Override
    public List<UniversityDto> findAll() {
        return universityRepository.findAll()
                .stream().map(x->modelMapper.map(x, UniversityDto.class)).collect(Collectors.toList());
    }

    //id si verilen üniversiteyi dönen kod blogu
    @Override
    public ServiceResult<UniversityDetailDto> getUniversity(int id) {

        ServiceResult<UniversityDetailDto> serviceResult=new ServiceResult<>();
        OneStudentDto oneStudentDto=new OneStudentDto();
        University university= universityRepository.getById(id);
        //üniversite bulunduysa üniversiteyi dönen kod blogu
        if (university!=null){
            serviceResult.setResult(modelMapper.map(university,UniversityDetailDto.class));
            return serviceResult ;
        }
        //bulunamadı durumunda hata mesajı döndüren kod blogu
        NotFound notFound=new NotFound("error",id+" numaralı üniversite kaydı bulunamadı");
        serviceResult.setNotFound(notFound);
        return  serviceResult;


    }
    //Üniversite kaydı yapan kod blogu
    @Override
    public University save(University university) {
        university.setCreatedAt(new Date());
        university.setUpdatedAt(new Date());
        return universityRepository.save(university);
    }

    //Apiden üniversite listesi çeken kod blogu
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

