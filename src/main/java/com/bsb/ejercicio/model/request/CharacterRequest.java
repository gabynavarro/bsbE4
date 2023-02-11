package com.bsb.ejercicio.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CharacterRequest {

    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<String> listMovies;


}
