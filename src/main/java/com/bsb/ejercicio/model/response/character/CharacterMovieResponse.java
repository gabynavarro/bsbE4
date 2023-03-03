package com.bsb.ejercicio.model.response.character;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Builder
@Getter
@Setter
public class CharacterMovieResponse {
    @ApiModelProperty(name = "name movie",
            value = "name of the movie",
            dataType = "String",
            example = "Titanic")
     private String title;
    @ApiModelProperty(name = "date movie",
            value = "date of the movie",
            dataType = "LocalDate",
            example = "2001-03-03")
    private LocalDate date;
    @ApiModelProperty(name = "score movie",
            value = "score of the movie",
            dataType = "Int",
            example = "3")
    private int score;
    public CharacterMovieResponse( String title, LocalDate date, int score) {
        this.title = title;
        this.date = date;
        this.score = score;
    }
}
