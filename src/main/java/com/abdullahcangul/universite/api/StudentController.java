package com.abdullahcangul.universite.api;

import com.abdullahcangul.universite.dto.StudentDto;
import com.abdullahcangul.universite.entity.Student;
import com.abdullahcangul.universite.service.impl.StudentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController  {

    private final StudentServiceImpl studentService;
    private final ModelMapper modelMapper;

    public StudentController(StudentServiceImpl studentService, ModelMapper modelMapper){
        this.studentService=studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<StudentDto>> getAllStudents(){

        return  ResponseEntity.ok(
                studentService.findAll()
                        .stream().map(x->{
                          StudentDto studentDto= modelMapper.map(x, StudentDto.class);
                          studentDto.setUniversity(x.getUniversity().getName());
                            return studentDto;
                        })
                        .collect(Collectors.toList())
        );

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
