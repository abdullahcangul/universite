package com.abdullahcangul.universite.service;

import com.abdullahcangul.universite.ViewModel.ViewUniversity;
import com.abdullahcangul.universite.entity.University;

import java.util.List;

public interface UniversityService {
    List<University> findAll();
    University getUniversity(int id);
    University save(University universities);
    List<University> getAllUniversity();
}
