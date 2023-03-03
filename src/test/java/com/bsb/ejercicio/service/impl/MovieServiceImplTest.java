package com.bsb.ejercicio.service.impl;

import com.bsb.ejercicio.datos.DatosDummy;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.exception.NotFoundException;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.model.mappers.MovieMapper;
import com.bsb.ejercicio.model.request.MovieRequest;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import com.bsb.ejercicio.repository.MovieRepository;
import com.bsb.ejercicio.service.IMovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MovieServiceImplTest {
    @Mock
    private MovieRepository repo;
    @Autowired
    private MovieMapper mapper;
    @Autowired
    private IMovieService service;
    private MovieResponse response;
    private Movie movie;
    private MovieRequest request;
    private List<MovieResponse> listResponse;
    private List<Movie> list;

    @BeforeEach
    void setUp() {
    //    list = DatosDummy.getAllMovie();
    //    list.forEach((c) -> repo.save(c));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findTitle() throws ErrorProcessException {
        String title = "movie1";
        movie = DatosDummy.getMovieOne();

        response = new MovieResponse();
        /*  Set response comparar con result */
        when(repo.findByTitle(title)).thenReturn(Optional.of(movie));
        mapper.toResponse(movie);
        MovieResponse result = service.findTitle(title);
        assertEquals(result.getTitle(), "movie1");
        assertNotNull(result);
        verify(repo).findByTitle(title);


    }

    @Test
    void getAll() throws NotFoundException, ErrorProcessException {
        list = DatosDummy.getAllMovie();
        when(repo.findAll()).thenReturn(DatosDummy.getAllMovie());
        listResponse = service.getAll();
        assertThat(listResponse.size()).isEqualTo(4);
        assertEquals(list, listResponse);
        verify(repo, times(1)).findAll();

    }

    @Test
    void findByGender() {
    }

    @Test
    void findByDate() throws ErrorProcessException {

        listResponse=DatosDummy.getAllMovie()
                .stream()
                .map(DatosDummy::movieTestResponse)
                .collect(Collectors.toList());

        LocalDate from = LocalDate.of(2022, 1, 1);
        LocalDate to = LocalDate.of(2022, 12, 31);

        when(repo.findByDate(from, to)).thenReturn(DatosDummy.getAllMovie());

        List<MovieResponse> result = service.findByDate(from, to);
     //   assertNotNull(result);
   //     assertThat(result).hasSize(3);
        verify(repo).findByDate(from,to);
        assertEquals(listResponse,result);
    }

    @Test
    void movieCreate() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

}