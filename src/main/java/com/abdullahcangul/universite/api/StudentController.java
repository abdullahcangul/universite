package com.abdullahcangul.universite.api;

import com.abdullahcangul.universite.entity.Student;
import com.abdullahcangul.universite.service.impl.StudentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController  {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService){
        this.studentService=studentService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Student>> getAllStudents(){
       List<Student> students= studentService.findAll();
       return ResponseEntity.ok(students);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(value="id") int id){
        Student student= studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Student> createStudent( @RequestBody Student student){
        return ResponseEntity.ok(studentService.save(student));
    }
}
