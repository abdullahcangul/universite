package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;

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
    @ApiModelProperty(required = true,name = "started_at",value = "started_at")
    private LocalDate started_at;

    public String getStarted_at() {
        return started_at.toString();
    }
}
