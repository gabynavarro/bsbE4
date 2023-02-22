package com.bsb.ejercicio.model.response;

import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
public class GenderResponse {
    private Long id;
    private String name;
    private List<MovieResponse> movieOrSeriesLis;
}
