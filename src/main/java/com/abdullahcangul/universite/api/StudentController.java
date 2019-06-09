package com.abdullahcangul.universite.api;

import com.abdullahcangul.universite.dto.*;
import com.abdullahcangul.universite.entity.errorModel.ServiceResult;
import com.abdullahcangul.universite.service.impl.StudentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController  {

    private final StudentServiceImpl studentService;
    private final ModelMapper modelMapper;

    public StudentController(StudentServiceImpl studentService, ModelMapper modelMapper){
        this.studentService=studentService;
        this.modelMapper = modelMapper;
    }
    //Kayıtlı tüm ögrencileri getirir
    @GetMapping(value = "")
    public ResponseEntity<List<StudentDto>> getAllStudents(){

        return  ResponseEntity.ok(studentService.findAll());
    }
    //id si gönderilen ögrenciyi getirir
    @GetMapping(value = "/{id}")
    public ResponseEntity getStudent(@PathVariable(value="id",required = true) int id){

        //ServiceResult servisten controller arası veri tasımaya yarayan nesnedir
        ServiceResult<OneStudentDto> serviceResult=studentService.getStudentById(id);
        //ögrenci bulunamazsa not found dönderen kod
       if (serviceResult.getNotFound()!=null)
           return new ResponseEntity<>(serviceResult.getNotFound(),HttpStatus.NOT_FOUND);
       return ResponseEntity.ok(serviceResult.getResult());

    }
    //ögrenci eklemeye yarayan kod blogu
    @PostMapping(value = "")
    public ResponseEntity<PostStudentResponseDto> createStudent(@Valid @RequestBody PostStudentDto postStudentDto){

       return ResponseEntity.ok(studentService.save(postStudentDto));
    }
}
