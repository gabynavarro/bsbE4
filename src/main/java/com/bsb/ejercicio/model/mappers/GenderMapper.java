package com.bsb.ejercicio.model.mappers;

import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.GenderResponse;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class GenderMapper {

    public GenderResponse toResponse(Gender gender){
        return GenderResponse.builder()
                .id(gender.getId())
                .name(gender.getName())
                .movieOrSeriesLis(gender.getMovieOrSeriesLis().stream().map(p->MovieResponse.builder()
                                .title(p.getTitle())
                                .date(p.getDate())
                                .build()).collect(Collectors.toList())
                        )
                .build();
    }

    public Gender toEntity(GenderRequest gender){
        return Gender.builder()
                .name(gender.getName())
                .movieOrSeriesLis(new ArrayList<>())
                .build();
    }
}
