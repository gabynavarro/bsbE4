package com.bsb.ejercicio.model.response.Gender;


import com.bsb.ejercicio.model.response.movie.MovieGenderResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
public class GenderResponse {
    private Long id;
    private String name;
    private List<MovieGenderResponse> movieOrSeriesLis;
}
