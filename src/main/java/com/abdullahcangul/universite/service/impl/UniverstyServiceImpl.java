package com.abdullahcangul.universite.service.impl;

import com.abdullahcangul.universite.entity.University;
import com.abdullahcangul.universite.repository.UniversityRepository;
import com.abdullahcangul.universite.service.UniversityService;
import org.springframework.stereotype.Service;

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
    public List<University> getAllUniversity(List<University> universities) {
        return universities;
    }


}
