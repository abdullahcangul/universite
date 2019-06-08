package com.abdullahcangul.universite.entity.errorModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResult<T>{

    private NotFound notFound;

    private T result;
}
