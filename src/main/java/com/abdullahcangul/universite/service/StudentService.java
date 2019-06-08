package com.abdullahcangul.universite.service;

import com.abdullahcangul.universite.dto.OneStudentDto;
import com.abdullahcangul.universite.dto.PostStudentDto;
import com.abdullahcangul.universite.dto.PostStudentResponseDto;
import com.abdullahcangul.universite.dto.StudentDto;
import com.abdullahcangul.universite.entity.errorModel.ServiceResult;

import java.util.List;

public interface StudentService  {

    List<StudentDto> findAll();
    ServiceResult<OneStudentDto> getStudentById(int id) ;
    PostStudentResponseDto save(PostStudentDto postStudentDto);
}
