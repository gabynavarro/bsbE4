package com.bsb.ejercicio.model.entity;

import lombok.*;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
public class Character {
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<Movie> listMovie;

    public Character(String name) {
        this.name = name;
    }
}
