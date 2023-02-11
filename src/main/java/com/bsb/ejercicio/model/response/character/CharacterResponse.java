package com.bsb.ejercicio.model.response.character;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class CharacterResponse {
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<CharacterMovieResponse> listMovie;

}
