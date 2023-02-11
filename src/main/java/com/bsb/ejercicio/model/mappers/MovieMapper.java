package com.bsb.ejercicio.model.mappers;

import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.model.request.MovieRequest;
import com.bsb.ejercicio.model.response.character.CharacterMovieResponse;
import com.bsb.ejercicio.model.response.GenderResponse;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class MovieMapper {
    @Autowired
    private  CharacterMapper characterMapper;
    public CharacterMovieResponse toResponse(Movie movie) {
        return CharacterMovieResponse.builder()
                .title(movie.getTitle())
                .date(movie.getDate())
                .score(movie.getScore())
                .build();
    }

    public Movie toEntity(MovieRequest movie) {
        return Movie.builder()
                .title(movie.getTitle())
                .date(movie.getDate())
                .score(movie.getScore())
                .build();
    }

    public MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .date(movie.getDate())
                .score(movie.getScore())
                .gender(GenderResponse.builder()
                        .id(movie.getGender().getId())
                        .name(movie.getGender().getName())
                        .build())
                .characters(movie.getCharacter().stream()
                        .map(characterMapper::toMovieCharacter )
                        .collect(Collectors.toList()))
                .build();
    }
}
