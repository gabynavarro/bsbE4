package com.bsb.ejercicio.model.entity;

import lombok.*;

import java.util.List;
@Builder
@Getter @Setter
public class Character {
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<Movie> listMovie;

    public Character(Long id, String name, Integer age, Double weight, String history, List<Movie> listMovie) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.listMovie = listMovie;
    }

    public Character(String name) {
        this.name = name;
    }
}
