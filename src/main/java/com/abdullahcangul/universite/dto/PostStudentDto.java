package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostStudentDto {

    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = true,value = "started_at")
    private String started_at;

    @ApiModelProperty(required = true)
    private Integer university;
}
