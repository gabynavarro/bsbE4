package com.bsb.ejercicio.model.response.movie;

import com.bsb.ejercicio.model.entity.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class MovieGenderResponse {
    private Long id;
    private String title;
    private LocalDate date;

    public static MovieGenderResponse toResponse(Movie movie) {
        return MovieGenderResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .date(movie.getDate())
                .build();
    }
}
