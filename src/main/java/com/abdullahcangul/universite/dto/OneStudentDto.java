package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OneStudentDto {
    @ApiModelProperty(required = true)
    private int id;
    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true,value = "started_at")
    private String startedAt;

    private OneStudentUniversityDto university;

}
