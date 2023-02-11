package com.bsb.ejercicio.repository.impl;

import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.repository.MovieRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private  final String[] nameCharater = {"Robert-Downey", "Chris-Evans", "Chris-Hemsworth",
            "Paul-Rudd", "Michael-Douglas",
            "Guy-Pearce", "Don-Cheadle"
    };

    private  final String[] titleMovie = {"Oblivion",
            "Titanic",
            "IronMan",
            "Mulholland"};
    private  final LocalDate[] date = {
            LocalDate.parse("2012-01-08"),
            LocalDate.parse("2014-08-15"),
            LocalDate.parse("2015-02-15"),
            LocalDate.parse("2013-10-15")};

    public  List<Gender> addGender() {
        return Arrays.asList(new Gender(1L, "Suspenso"),
                new Gender(2L, "Terror"),
                new Gender(3L, "Suspenso"),
                new Gender(4L, "Comedia")
        );
    }

    public  List<Movie> addMovie() {
        List<Gender> listGender = addGender();

        return Arrays.asList(
                new Movie(1L, titleMovie[0], date[0], 3,
                        Arrays.asList(new Character(nameCharater[0]), new Character(nameCharater[1]), new Character(nameCharater[2])),
                        listGender.get(0)),
                new Movie(2L, titleMovie[1], date[1], 2,
                        Arrays.asList(new Character(nameCharater[0]), new Character(nameCharater[1]), new Character(nameCharater[2])),
                        listGender.get(1)),
                new Movie(3L, titleMovie[2], date[2], 5,
                        Arrays.asList(new Character(nameCharater[3]), new Character(nameCharater[4])),
                        listGender.get(2)),
                new Movie(4L, titleMovie[3], date[3], 4,
                        Arrays.asList(new Character(nameCharater[5]), new Character(nameCharater[6])),
                        listGender.get(3))
        );
    }
    @Override
    public List<Movie> findTitle(String title) {
        List<Movie> movies = this.addMovie();
        return movies.stream()
                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getAll() {
        return new ArrayList<>(this.addMovie());
    }

    @Override
    public List<Movie> findByGender(String gender) {
        List<Movie> movies = this.addMovie();
        return movies.stream()
                .filter(m -> m.getGender().getName().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findByDate(LocalDate from, LocalDate to) {
        List<Movie> movies = this.addMovie();
        return movies.stream()
                .filter(m -> m.getDate().isAfter(from) && m.getDate().isBefore(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> findByScore(int from, int to) {
        List<Movie> movies = this.addMovie();
        return movies.stream()
                .filter(m -> m.getScore() >= from && m.getScore() <= to)
                .collect(Collectors.toList());
    }
    @Override
    public List<Movie> movieCreate(Movie movie) {
        List<Movie> movies = getAll();
        movie.setId((long) (movies.size() + 1));
        movies.add(movies.size(),movie);
        return movies;
    }

    @Override
    public Movie findById(Long id) {
        List<Movie> movies = this.addMovie();
        return movies.stream().filter(g -> Objects.equals(g.getId(), id)).findFirst().orElse(null);
    }
}
