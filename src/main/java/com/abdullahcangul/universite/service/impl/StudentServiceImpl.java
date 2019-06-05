package com.abdullahcangul.universite.service.impl;

import com.abdullahcangul.universite.ViewModel.ViewUniversity;
import com.abdullahcangul.universite.entity.Student;
import com.abdullahcangul.universite.entity.University;
import com.abdullahcangul.universite.repository.StudentRepository;
import com.abdullahcangul.universite.repository.UniversityRepository;
import com.abdullahcangul.universite.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final UniverstyServiceImpl universtyService;

    public StudentServiceImpl(UniverstyServiceImpl universtyService,StudentRepository studentRepository,UniversityRepository universityRepository){
        this.studentRepository=studentRepository;
        this.universityRepository=universityRepository;
        this.universtyService=universtyService;
    }


    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public Student save(Student student) {

     University university=universtyService.getUniversity(student.getUniversity().getId());
     if(university==null){
         //ToDo:istekte bulunulacak, üniversiteyi veritabanına kaydedilecek
         university= (University) universtyService.getAllUniversity().stream()
               .filter(x->x.getId()==student.getUniversity().getApiId()).findFirst().get();

       university.setApiId(university.getId());
     }
     student.setUniversity(university);

     Date date = new Date();
     student.setStartedAt(date);
     universtyService.save(university);

     return student;
    }

}
