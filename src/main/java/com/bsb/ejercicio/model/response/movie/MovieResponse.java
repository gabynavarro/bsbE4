package com.bsb.ejercicio.model.response.movie;


import com.bsb.ejercicio.model.response.Gender.GenderMovieResponse;
import com.bsb.ejercicio.model.response.Gender.GenderResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Builder
@Setter
@Getter
public class MovieResponse {
    private Long id;
    private String title;
    private LocalDate date;
    private int score;
    private List<MovieCharacterResponse> characters;
    private GenderMovieResponse gender;
}
