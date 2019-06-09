package com.abdullahcangul.universite.api;

import com.abdullahcangul.universite.dto.UniversityDetailDto;
import com.abdullahcangul.universite.dto.UniversityDto;
import com.abdullahcangul.universite.entity.errorModel.ServiceResult;
import com.abdullahcangul.universite.service.impl.UniverstyServiceImpl;
import org.springframework.http.HttpStatus;
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
    //Kayıtlı bütün üniversiteleri getiren kod blogu
    @GetMapping(value = "")
    public ResponseEntity<List<UniversityDto>> getAllUniversities(){

        return ResponseEntity.ok(universityService.findAll());
    }
    //id si verilen üniversiteyi döndüren kod blogu
    @GetMapping(value = "/{id}")
    public ResponseEntity getUniversity(@PathVariable(value="id",required = true) int id){

        ServiceResult<UniversityDetailDto> serviceResult=universityService.getUniversity(id);
        //id si verilen üniversite bulunamadıgında döndürülen kod blogu
        if (serviceResult.getNotFound()!=null)
            return new ResponseEntity<>(serviceResult.getNotFound(), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(serviceResult.getResult());

    }


}
