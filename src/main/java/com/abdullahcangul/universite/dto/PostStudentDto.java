package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostStudentDto {

    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = true,value = "started_at")
    private String startedAt;

    @ApiModelProperty(required = true)
    private Integer university;
}
