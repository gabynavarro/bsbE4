package com.bsb.ejercicio.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET softDeleted = true WHERE id=?")
@Where(clause = "soft_deleted = false")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private boolean softDeleted=false;


    @ManyToMany()
    @JoinTable(
            name = "characters_movies",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> listMovie;

    public Character(Long id,String name, Integer age, Double weight, String history, List<Movie> listMovie) {
        this.id=id;
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
