package com.bsb.ejercicio.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    @ManyToMany()
    @JoinTable(
            name = "characters_movies",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> listMovie;

    public Character(String name) {
        this.name = name;
    }
}
