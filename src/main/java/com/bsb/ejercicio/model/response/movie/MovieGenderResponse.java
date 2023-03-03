package com.bsb.ejercicio.model.response.movie;

import com.bsb.ejercicio.model.entity.Movie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@ApiModel(value = "MovieGenderResponse", description = "response of movie in a list genders")
public class MovieGenderResponse {
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

    public static MovieGenderResponse toResponse(Movie movie) {
        return MovieGenderResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .date(movie.getDate())
                .build();
    }
}
