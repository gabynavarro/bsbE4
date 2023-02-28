package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.datos.DatosDummy;
import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.validations.Validations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    @BeforeEach
    void setUp() {
        List<Movie> list = DatosDummy.addMovie();
        list.forEach((m) -> movieRepository.save(m));
    }

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void findByTitle() {

        //GIVEN
        String movie="Titanic";
        //WHEN
        Movie m = movieRepository.findByTitle(movie).orElse(null);
        //THEN
        assertThat(m!=null).isTrue();
        assertThat(m.getTitle())
                .isEqualTo(movie);
    }


    @Test
    void findByDate() {
        //GIVEN
        LocalDate date=  LocalDate.parse("2012-01-08");
        LocalDate date1=  LocalDate.parse("2013-01-08");
        //WHEN
        List<Movie> m = movieRepository.findByDate(date,date1);
        //THEN
        assertThat(!m.isEmpty()).isTrue();
    }

    @Test
    void findByScore() {
        //GIVEN
        int score1= 2;
        int score2= 4;
        //WHEN
        List<Movie> m = movieRepository.findByScore(score1,score2);
        //THEN
        assertThat(!m.isEmpty()&&m.get(0).getScore()>=score1&&m.get(0).getScore()<=score2).isTrue();
    }
}