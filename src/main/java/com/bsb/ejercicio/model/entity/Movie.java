package com.bsb.ejercicio.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE movies SET softDeleted = true WHERE id=?")
@Where(clause = "soft_deleted = false")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate date;
    private int score;
    private boolean softDeleted=false;
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