package com.bsb.ejercicio.model.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterRequest {

    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<String> listMovies;


}
