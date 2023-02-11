package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository {
    List<Movie> findTitle(String title);

    List<Movie> getAll();

    List<Movie> findByGender(String gender);

    List<Movie> findByDate(LocalDate from, LocalDate to);

    List<Movie> findByScore(int from, int to);

    List<Movie> movieCreate(Movie movie);

    Movie findById(Long id);

}
