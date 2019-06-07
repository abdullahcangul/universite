package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDetailDto {

    @ApiModelProperty(required = true)
    private int id;

    @ApiModelProperty(required = true,value = "api_id")
    private int apiId;

    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = true)
    private String city;
    @ApiModelProperty(required = true,value = "web_page")
    private String webPage;

    @ApiModelProperty(required = true)
    private String type;

    @ApiModelProperty(required = true,value = "founded_at")
    private Date foundedAt;


    private List<UniversityDetailStudentDto> students;
}
