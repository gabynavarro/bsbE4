package com.bsb.ejercicio.model.response.character;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Builder
@Getter
@Setter
public class CharacterMovieResponse {
     private String title;
    private LocalDate date;
    private int score;
    public CharacterMovieResponse( String title, LocalDate date, int score) {
        this.title = title;
        this.date = date;
        this.score = score;
    }
}
