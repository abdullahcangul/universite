package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDetailStudentDto {
    @ApiModelProperty(required = true)
    private int id;

    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true,value = "started_at")
    private Date startedAt;
}
