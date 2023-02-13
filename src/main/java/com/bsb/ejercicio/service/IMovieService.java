package com.bsb.ejercicio.service;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.exception.NotFoundException;
import com.bsb.ejercicio.model.request.MovieRequest;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

public interface IMovieService {
     MovieResponse findTitle(String title) throws ResponseStatusException, ErrorProcessException;
     List<MovieResponse> getAll() throws NotFoundException, ErrorProcessException;
     List<MovieResponse> findByGender(String gender) throws ErrorProcessException;
     List<MovieResponse> findByDate(LocalDate from, LocalDate to) throws ErrorProcessException;
     List<MovieResponse> findByScore(int from, int to) throws ErrorProcessException;
     MovieResponse movieCreate(MovieRequest movie) throws BadRequestException, ErrorProcessException;
     MovieResponse findById(Long id) throws ErrorProcessException;
     MovieResponse update(Long id, MovieRequest movie) throws ErrorProcessException;
}
