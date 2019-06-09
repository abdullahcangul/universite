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

import java.util.Date;
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

    //Kayıtlı tüm ögrencileri dto objesine cevirip gönderen kod blogu
    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll()
                .stream().map(x->{
            StudentDto studentDto= modelMapper.map(x, StudentDto.class);
            studentDto.setUniversity(x.getUniversity().getName());
            return studentDto;
        }).collect(Collectors.toList());
    }

    //idsi verilen ögrenciyi dto objesine cevirip dönen kod blogu
    @Override
    public ServiceResult<OneStudentDto> getStudentById(int id)  {
        ServiceResult<OneStudentDto> serviceResult=new ServiceResult<>();
        OneStudentDto oneStudentDto=new OneStudentDto();
        Student student= studentRepository.getStudentById(id);
        //Ögrenci bulunursa getiren kod blogu
        if (student!=null){
            serviceResult.setResult(modelMapper.map(student,OneStudentDto.class));
            return serviceResult ;
        }
        //Ögrenci bulunamazsa gerekli hata mesaj ı dönderen kod
        NotFound notFound=new NotFound("error",id+" numaralı Ögrenci kaydı bulunamadı");
        serviceResult.setNotFound(notFound);
        return  serviceResult;

    }
    //Ögrenci ekleyen kod blogu
    @Override
    public PostStudentResponseDto save(PostStudentDto postStudentDto) {

     Student student= modelMapper.map(postStudentDto,Student.class);
     University universityForDto=new University();
     universityForDto.setApiId(postStudentDto.getUniversity());
     student.setUniversity(universityForDto);
     student.setStartedAt(postStudentDto.getStarted_at());
     student.setCreatedAt(new Date());
     student.setUpdatedAt(new Date());

    //Ögrencinin ait oldugu üniversiteyi bulan kod parçası
     University university=universityRepository.getByApiId(student.getUniversity().getApiId());
     //üniversite bulunmazsa yeni üniversite olusturan kod blogu
     if(university==null){
         //UniversiteServiceImpl içinde apiden çekilen, üniversiteler içinden ögrenciye ait üniversiteyi dönen kod parcası
         university= (University) universtyService.getAllUniversity().stream()
               .filter(x->x.getId()==student.getUniversity().getApiId()).findFirst().get();
       university.setCreatedAt(new Date());
       university.setUpdatedAt(new Date());
       university.setApiId(university.getId());
       university.setId(0);
     }
     student.setUniversity(university);
     Student StudentResult=studentRepository.save(student);
     PostStudentResponseDto studentDto=new PostStudentResponseDto();
     //Ögrenci Kayıt oldumu kontrolü
     if (StudentResult!=null) {
         studentDto.setId(StudentResult.getUniversity().getId());
         studentDto.setStatus(Status.success);
         studentDto.setMessage(StudentResult.getName() + " adlı öğrenci Karabük Üniversitesine başarıyla eklendi.");
     }
     return studentDto;
    }

}
