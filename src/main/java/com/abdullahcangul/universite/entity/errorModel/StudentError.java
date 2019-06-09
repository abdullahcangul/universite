package com.abdullahcangul.universite.entity.errorModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentError {

    public String key;

    public String errors;
}
