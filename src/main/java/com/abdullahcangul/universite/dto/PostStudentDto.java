package com.abdullahcangul.universite.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostStudentDto {

    @ApiModelProperty(required = true)
    @NotNull(message = "Öğrenci adı boş bırakılamaz")
    //@Max(100)
    private String name;

    @ApiModelProperty(required = true,name ="started_at",value = "started_at")
    @NotNull(message = "Baslangıç tarihi girilimeli")
    @Past(message = "Üniversiteye başlangıç tarihi bugünden büyük olamaz.")
    private LocalDate started_at;

    @ApiModelProperty(required = true)
    private Integer university;
}
