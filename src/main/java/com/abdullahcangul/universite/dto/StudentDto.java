package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @ApiModelProperty(required = true)
    private int id;

    @ApiModelProperty(required = true)
    private String name;


    @ApiModelProperty(required = true)
    private String university;
}
