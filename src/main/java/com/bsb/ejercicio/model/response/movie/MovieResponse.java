package com.bsb.ejercicio.model.response.movie;


import com.bsb.ejercicio.model.response.Gender.GenderMovieResponse;
import com.bsb.ejercicio.model.response.Gender.GenderResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Builder
@Setter
@Getter
@ApiModel(value = "MovieResponse", description = "response of models movie")
@AllArgsConstructor @NoArgsConstructor
public class MovieResponse {
    @ApiModelProperty(name = "id",
            value = "id movie",
            dataType = "Long",
            example = "5")
    private Long id;
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
            example = "4")
    private int score;
    @ApiModelProperty(name = "characters",
            value = "list charactes of the movie",
            dataType = "List"
            )
    private List<MovieCharacterResponse> characters;
    @ApiModelProperty(name = "gender",
            value = "gender of the movie",
            dataType = "GenderMovieResponse"
    )
    private GenderMovieResponse gender;
}
