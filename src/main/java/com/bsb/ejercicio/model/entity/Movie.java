package com.bsb.ejercicio.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate date;
    private int score;
    @ManyToMany(mappedBy = "listMovie", fetch = FetchType.LAZY)
    private List<Character> character = new ArrayList<>();
    @OneToOne
    private Gender gender;

    public Movie(Long id, String title, LocalDate date, int score, Gender gender) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.score = score;
        this.gender = gender;
    }

    public Movie(String title, LocalDate date, int i) {
        this.title = title;
        this.date = date;
        this.score = i;
    }
}