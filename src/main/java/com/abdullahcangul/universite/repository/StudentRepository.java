package com.abdullahcangul.universite.repository;

import com.abdullahcangul.universite.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAll();
    Student getStudentById(int id);
}
