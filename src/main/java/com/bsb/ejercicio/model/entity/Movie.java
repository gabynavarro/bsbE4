package com.bsb.ejercicio.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("Model Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty("Movie title")
    private String title;
    @ApiModelProperty("Movie date, format (aaaa-mm-dd)")
    private LocalDate date;
    @ApiModelProperty("Movie score 1-5")
    private int score;
    private boolean softDeleted=false;
    @ApiModelProperty("List characters in movie ")
    @ManyToMany(mappedBy = "listMovie", fetch = FetchType.LAZY)
    private List<Character> character = new ArrayList<>();
    @OneToOne
    @ApiModelProperty("Movie type gender ")
    private Gender gender;

    public Movie(Long id, String title, LocalDate date, int score, List<Character> character, Gender gender) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.score = score;
        this.character = character;
        this.gender = gender;
    }

    public Movie(Long id, String title, LocalDate date, int score, Gender gender) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.score = score;
        this.gender = gender;
    }
    public Movie(long id, String title, LocalDate date, boolean b) {
        this.title = title;
        this.date = date;

        this.softDeleted=b;
    }
    public Movie(String title, LocalDate date, int score, Gender gender) {
        this.title = title;
        this.date = date;
        this.score = score;
        this.gender = gender;
    }


}