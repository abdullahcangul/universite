package com.abdullahcangul.universite.service;

import com.abdullahcangul.universite.entity.Student;

import java.util.List;

public interface StudentService  {

    List<Student> findAll();
    Student getStudentById(int id);
    Student save(Student student);
}
