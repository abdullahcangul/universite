package com.abdullahcangul.universite.service.impl;

import com.abdullahcangul.universite.entity.Student;
import com.abdullahcangul.universite.entity.University;
import com.abdullahcangul.universite.repository.StudentRepository;
import com.abdullahcangul.universite.repository.UniversityRepository;
import com.abdullahcangul.universite.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;

    public StudentServiceImpl(StudentRepository studentRepository,UniversityRepository universityRepository){
        this.studentRepository=studentRepository;
        this.universityRepository=universityRepository;
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

     University university=universityRepository.getById(student.getUniversity().getId());
     if(university==null){
         //ToDo:istekte bulunulacak, üniversiteyi veritabanına kaydedilecek
     }
     student.setUniversity(university);
     return student;
    }

}
