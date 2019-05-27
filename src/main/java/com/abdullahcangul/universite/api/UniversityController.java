package com.abdullahcangul.universite.api;

import com.abdullahcangul.universite.entity.Student;
import com.abdullahcangul.universite.entity.University;
import com.abdullahcangul.universite.service.impl.StudentServiceImpl;
import com.abdullahcangul.universite.service.impl.UniverstyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniverstyServiceImpl universityService;

    public UniversityController(UniverstyServiceImpl universityService){
        this.universityService=universityService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<University>> getAllUniversities(){
        List<University> universities= universityService.findAll();
        return ResponseEntity.ok(universities);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable(value="id") int id){
        University university= universityService.getUniversity(id);
        return ResponseEntity.ok(university);
    }

}
