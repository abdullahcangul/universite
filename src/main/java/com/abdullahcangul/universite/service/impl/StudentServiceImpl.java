package com.abdullahcangul.universite.service.impl;

import com.abdullahcangul.universite.dto.*;
import com.abdullahcangul.universite.entity.Student;
import com.abdullahcangul.universite.entity.University;
import com.abdullahcangul.universite.entity.errorModel.NotFound;
import com.abdullahcangul.universite.entity.errorModel.ServiceResult;
import com.abdullahcangul.universite.repository.StudentRepository;
import com.abdullahcangul.universite.repository.UniversityRepository;
import com.abdullahcangul.universite.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final ModelMapper modelMapper;
    private final UniverstyServiceImpl universtyService;

    public StudentServiceImpl(UniverstyServiceImpl universtyService, StudentRepository studentRepository, UniversityRepository universityRepository, UniversityRepository universityRepository1, ModelMapper modelMapper){
        this.studentRepository=studentRepository;
        this.universtyService=universtyService;
        this.universityRepository = universityRepository1;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll()
                .stream().map(x->{
            StudentDto studentDto= modelMapper.map(x, StudentDto.class);
            studentDto.setUniversity(x.getUniversity().getName());
            return studentDto;
        }).collect(Collectors.toList());
    }

    @Override
    public ServiceResult<OneStudentDto> getStudentById(int id)  {
        ServiceResult<OneStudentDto> serviceResult=new ServiceResult<>();
        OneStudentDto oneStudentDto=new OneStudentDto();
        Student student= studentRepository.getStudentById(id);
        if (student!=null){
            serviceResult.setResult(modelMapper.map(student,OneStudentDto.class));
            return serviceResult ;
        }
        NotFound notFound=new NotFound("error",id+" numaralı Ögrenci kaydı bulunamadı");
        serviceResult.setNotFound(notFound);
        return  serviceResult;

    }

    @Override
    public PostStudentResponseDto save(PostStudentDto postStudentDto) {

     Student student= modelMapper.map(postStudentDto,Student.class);
     University universityForDto=new University();
     universityForDto.setApiId(postStudentDto.getUniversity());
     student.setUniversity(universityForDto);

     University university=universityRepository.getByApiId(student.getUniversity().getApiId());
     if(university==null){
         university= (University) universtyService.getAllUniversity().stream()
               .filter(x->x.getId()==student.getUniversity().getApiId()).findFirst().get();

       university.setApiId(university.getId());
       university.setId(0);
     }
     else{

     }
     student.setUniversity(university);

     return modelMapper.map(studentRepository.save(student),PostStudentResponseDto.class);

    }

}
