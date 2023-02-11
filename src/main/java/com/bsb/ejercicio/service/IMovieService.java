package com.bsb.ejercicio.service;

import com.bsb.ejercicio.model.request.MovieRequest;
import com.bsb.ejercicio.model.response.movie.MovieResponse;

import java.time.LocalDate;
import java.util.List;

public interface IMovieService {
     List<MovieResponse> findTitle(String title);
     List<MovieResponse> getAll();
     List<MovieResponse> findByGender(String gender);
     List<MovieResponse> findByDate(LocalDate from, LocalDate to);
     List<MovieResponse> findByScore(int from, int to);
     List<MovieResponse> movieCreate(MovieRequest movie);
     MovieResponse findById(Long id);
     MovieResponse update(Long id, MovieRequest movie);
}
