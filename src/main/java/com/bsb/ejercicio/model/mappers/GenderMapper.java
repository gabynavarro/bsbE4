package com.bsb.ejercicio.model.mappers;

import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.GenderResponse;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {

    public GenderResponse toResponse(Gender gender){
        return GenderResponse.builder()
                .id(gender.getId())
                .name(gender.getName())
                .build();
    }

    public Gender toEntity(GenderRequest gender){
        return Gender.builder()
                .name(gender.getName())
                .build();
    }
}
