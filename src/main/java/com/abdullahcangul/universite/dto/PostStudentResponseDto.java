package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostStudentResponseDto {
    @ApiModelProperty(required = true)
    private int id;
    @ApiModelProperty(required = true)
    private Status Status;
    @ApiModelProperty(required = true)
    @Column(nullable = false,length = 100)
    private String message;
}
