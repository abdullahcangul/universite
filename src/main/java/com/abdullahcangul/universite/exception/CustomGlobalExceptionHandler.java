package com.abdullahcangul.universite.exception;

import com.abdullahcangul.universite.dto.Status;
import com.abdullahcangul.universite.entity.errorModel.StudentError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//validasyon larda hata olması durumunda girilen kod blogu
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", Status.error);
        body.put("message","Öğrenci eklenirken hata oluştu");


        //Tüm hata mesajlarını getirir
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        //tüm hatalı alanların adını getiren kod blogu
        List<String> keys = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField())
                .collect(Collectors.toList());

        List<StudentError> studentErrorList=new ArrayList<>();

        //Hata mesajlarını ve hatalı alanların isimlerini istenen formatta getiren kod blogu
        for (int i=0;i<errors.size();i++){
            studentErrorList.add(new StudentError(keys.get(i),errors.get(i)));
        }

        body.put("errors", studentErrorList);

        return new ResponseEntity<>(body, headers, status);

    }
}
