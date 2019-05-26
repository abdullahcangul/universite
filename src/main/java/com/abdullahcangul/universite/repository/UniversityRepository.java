package com.abdullahcangul.universite.repository;

import com.abdullahcangul.universite.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University,Integer> {

    List<University> findAll();
    University getById(int id);
}
