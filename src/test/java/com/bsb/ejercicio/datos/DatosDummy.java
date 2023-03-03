package com.bsb.ejercicio.datos;

import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.response.movie.MovieResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatosDummy {
    public static final String[] nameCharater = {"Gabriel", "Chris Evans", "Chris Hemsworth",
            "Paul-Rudd", "Michael Douglas",
            "Guy Pearce", "Don Cheadle"
    };
    public static final String[] descriptionCharacter = {"Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity...",
            "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity...",
            "Armed with a super-suit with the astonishing ability to shrink in scale but increase in strength, cat burglar Scott Lang must embrace his inner hero and help...",
            "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution."
    };

    public static final String[] titleMovie = {"Oblivion",
            "Titanic",
            "IronMan",
            "Mulholland"};
    public static final LocalDate[] date = {
            LocalDate.parse("2012-01-08"),
            LocalDate.parse("2014-08-15"),
            LocalDate.parse("2015-02-15"),
            LocalDate.parse("2013-10-15")};

    public static List<Gender> addGender() {
        return Arrays.asList(
                new Gender(1L, "Suspenso", new ArrayList<>(), false),
                new Gender(2L, "Terror", new ArrayList<>(), false),
                new Gender(3L, "Suspenso", new ArrayList<>(), false),
                new Gender(4L, "Comedia", new ArrayList<>(), false));
    }
    public static Movie onlyMovie(){
        return new Movie(1L, "Oblivion", date[0], 3,new Gender());
               // new Movie(1L, "Titanic", LocalDate.of(2022, 2, 1), 4);
    }

    public static Character onlyCahracter() {
        return Character.builder()
                .id(1L)
                .age(45)
                .history(descriptionCharacter[0])
                .weight(62.5)
                .name("Jorge-Perez")
                .listMovie(new ArrayList<>())
                .build();
    }
    public static Movie getMovieOne(){
        return  new Movie(1L, "movie1", LocalDate.of(2022, 2, 1), Boolean.FALSE);}

    public static Movie getMovieTwo(){
        return  new Movie(2L, "movie2", LocalDate.of(2022, 6, 1), Boolean.FALSE);

    }
    public static Movie getMovieThree() {
        return new Movie(3L, "movie3", LocalDate.of(2023, 6, 1), Boolean.FALSE);
    }
    public static List<Movie> getAllMovie() {return new ArrayList<>(Arrays.asList(
            new Movie(1L, "movie1", LocalDate.of(2022, 2, 1), Boolean.FALSE),
            new Movie(1L, "movie1", LocalDate.of(2022, 2, 1), Boolean.FALSE),
            new Movie(1L, "movie1", LocalDate.of(2022, 2, 1), Boolean.FALSE)));}

    public static MovieResponse movieTestResponse(Movie m){
        return MovieResponse.builder()
                .id(m.getId())
                .title(m.getTitle())
                .date(m.getDate())
                .score(m.getScore())
                .build();
    }
    public static List<Character> addCharacter() {
        return Arrays.asList(
                new Character(1L, nameCharater[0], 63, 65.5, descriptionCharacter[0],
                      new ArrayList<>()),
                new Character(2L, nameCharater[1], 63, 62.7, descriptionCharacter[0],
                        new ArrayList<>()),
                new Character(3L, nameCharater[2], 31, 58.3, descriptionCharacter[0],
                       new ArrayList<>()),
                new Character(4L, nameCharater[3], 65, 68.7, descriptionCharacter[1],
                       new ArrayList<>()),
                new Character(5L, nameCharater[4], 52, 64.7, descriptionCharacter[1],
                       new ArrayList<>()),
                new Character(6L, nameCharater[5], 36, 72.5, descriptionCharacter[2],
                        new ArrayList<>()),
                new Character(7L, nameCharater[6], 38, 82.5, descriptionCharacter[2],
                        new ArrayList<>())
        );
    }

    public static List<Movie> addMovie() {
        List<Gender> listGender = addGender();

        return Arrays.asList(
                new Movie(1L, titleMovie[0], date[0], 3,
                       new ArrayList<>(),
                      new Gender()),
                new Movie(2L, titleMovie[1], date[1], 2,
                       new ArrayList<>(),
                        new Gender()),
                new Movie(3L, titleMovie[2], date[2], 5,
                        new ArrayList<>(),
                        new Gender()),
                new Movie(4L, titleMovie[3], date[3], 4,
                        new ArrayList<>(),
                        new Gender())
        );
    }
}
