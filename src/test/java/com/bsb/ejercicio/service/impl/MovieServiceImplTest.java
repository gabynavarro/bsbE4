package com.bsb.ejercicio.service.impl;

import com.bsb.ejercicio.datos.DatosDummy;
import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.exception.NotFoundException;
import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.model.mappers.MovieMapper;
import com.bsb.ejercicio.model.request.MovieRequest;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import com.bsb.ejercicio.repository.GenderRepository;
import com.bsb.ejercicio.repository.MovieRepository;
import com.bsb.ejercicio.service.IGenderService;
import com.bsb.ejercicio.service.IMovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceImplTest {
    @MockBean
    MovieRepository repo;
    @MockBean
    GenderRepository genderRepository;
    @MockBean
    IGenderService gender;
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
        list = DatosDummy.addMovie();
        list.forEach((c) -> repo.save(c));
        listResponse = list.stream().map(DatosDummy::movieTestResponse).collect(Collectors.toList());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Find by Title")
    void findTitle() throws ErrorProcessException {
        String title = "movie1";
        when(repo.findByTitle(title)).thenReturn(Optional.of(DatosDummy.getMovieOne()));
        response = service.findTitle(title);
        assertThat(response != null).isTrue();
        assertEquals(response.getTitle(), DatosDummy.getMovieOne().getTitle());
        verify(repo).findByTitle(anyString());
    }

    @Test
    @DisplayName("Find all movie")
    void getAll() throws NotFoundException, ErrorProcessException {
        when(repo.findAll())
                .thenReturn(DatosDummy.getAllMovie());
        List<MovieResponse> result = service.getAll();
        assertThat(result.size() > 1).isTrue();
        assertEquals(result.size(), DatosDummy.getAllMovie().size());
        verify(this.repo).findAll();
    }

    @Test
    @DisplayName("Find by Gender")
    void findByGender() throws ErrorProcessException {
        Gender gender = DatosDummy.oneGender();
        when(repo.findByGenderName(gender.getName())).thenReturn(DatosDummy.getAllMovie());
        listResponse = service.findByGender(gender.getName());
        assertNotNull(listResponse);
        assertEquals(listResponse.get(0).getGender().getName(), gender.getName());
        assertThat(listResponse.size()).isEqualTo(3);
        verify(repo).findByGenderName(anyString());
    }

    @Test
    @DisplayName("Find by date")
    void findByDate() throws ErrorProcessException {
        LocalDate from = LocalDate.of(2022, 1, 1);
        LocalDate to = LocalDate.of(2022, 12, 31);
        when(repo.findByDate(from, to)).thenReturn(DatosDummy.getAllMovie());
        List<MovieResponse> result = service.findByDate(from, to);
        assertThat(result != null).isTrue();
        assertThat(result.isEmpty()).isFalse();
        assertEquals(result.get(0).getDate(), DatosDummy.getAllMovie().get(0).getDate());
        verify(repo)
                .findByDate(any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    @DisplayName("Create movie")
    void movieCreate() throws BadRequestException, ErrorProcessException {
        request = MovieRequest.builder()
                .date(LocalDate.parse("2012-01-08"))
                .title("Oso")
                .score(3)
                .idCharacters(Arrays.asList("1", "3"))
                .gender(1)
                .build();

        Movie myMovie = Movie.builder()
                .date(LocalDate.parse("2012-01-08"))
                .title("Oso")
                .score(3)
                .gender(new Gender())
                .character(new ArrayList<>())
                .build();
        when(repo.save(any(Movie.class))).thenReturn(myMovie);
        MovieResponse result = service.movieCreate(request);
        assertThat(result != null).isTrue();
        assertEquals(result.getTitle(), request.getTitle());
        verify(repo).findByTitle(anyString());
        verify(genderRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Find by id movie")
    void findById() throws BadRequestException, ErrorProcessException {
        movie = Movie.builder()
                .id(1L)
                .date(LocalDate.parse("2012-01-08"))
                .title("Oso")
                .score(3)
                .gender(new Gender())
                .character(new ArrayList<>())
                .build();

        when(repo.findById(anyLong())).thenReturn(Optional.of(DatosDummy.getMovieOne()));
        MovieResponse result = service.findById(movie.getId());
        assertNotNull(result);
        assertEquals(result.getId(), movie.getId());
        verify(repo).findById(anyLong());
    }

    @Test
    @DisplayName("update movie by id")
    void update() throws ErrorProcessException {
        request = MovieRequest.builder()
                .date(LocalDate.parse("2012-01-08"))
                .title("Oso")
                .score(3)
                .gender(1)
                .idCharacters(new ArrayList<>())
                .build();
        movie = DatosDummy.getMovieOne();
        when(repo.findById(anyLong())).thenReturn(Optional.ofNullable(movie));
        when(repo.save(any(Movie.class))).thenReturn(movie);
        MovieResponse result = service.update(DatosDummy.getMovieOne().getId(), request);
        assertNotNull(result);
        assertEquals(result.getTitle(), "Oso");
        verify(repo).findById(anyLong());
    }

}