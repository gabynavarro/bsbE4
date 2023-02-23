package com.bsb.ejercicio.model.response.Gender;

import com.bsb.ejercicio.model.entity.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenderMovieResponse {
    private Long id;
    private String name;
    public static GenderMovieResponse toResponse(Gender enty){
        return GenderMovieResponse.builder()
                .id(enty.getId())
                .name(enty.getName())
                .build();
    }
}
