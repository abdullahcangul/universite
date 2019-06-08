package com.abdullahcangul.universite.service;

import com.abdullahcangul.universite.dto.UniversityDetailDto;
import com.abdullahcangul.universite.dto.UniversityDto;
import com.abdullahcangul.universite.entity.University;
import com.abdullahcangul.universite.entity.errorModel.ServiceResult;

import java.util.List;

public interface UniversityService {
    List<UniversityDto> findAll();
    ServiceResult<UniversityDetailDto> getUniversity(int id);
    University save(University universities);
    List<University> getAllUniversity();
}
