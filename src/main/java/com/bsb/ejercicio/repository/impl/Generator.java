package com.bsb.ejercicio.repository.impl;

import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.entity.Movie;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class Generator {
    private static final String[] nameCharater = {"Robert-Downey", "Chris-Evans", "Chris-Hemsworth",
            "Paul-Rudd", "Michael-Douglas",
            "Guy-Pearce", "Don-Cheadle"
    };
    private static final String[] descriptionCharacter = {"Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity...",
            "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity...",
            "Armed with a super-suit with the astonishing ability to shrink in scale but increase in strength, cat burglar Scott Lang must embrace his inner hero and help...",
            "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution."
    };

    private static final String[] titleMovie = {"Oblivion",
            "Titanic",
            "IronMan",
            "Mulholland"};
    private static final LocalDate[] date = {
            LocalDate.parse("2012-01-08"),
            LocalDate.parse("2014-08-15"),
            LocalDate.parse("2015-02-15"),
            LocalDate.parse("2013-10-15")};

    public static List<Gender> addGender() {
        return Arrays.asList(
                new Gender(1L, "Suspenso"),
                new Gender(2L, "Terror"),
                new Gender(3L, "Suspenso"),
                new Gender(4L, "Comedia"));
    }


    public static List<Character> addCharacter() {
        return Arrays.asList(
                new Character(1L, nameCharater[0], 63, 65.5, descriptionCharacter[0],
                        Arrays.asList(
                                new Movie(titleMovie[0], date[0], 5),
                                new Movie(titleMovie[1], date[1], 3))),
                new Character(2L, nameCharater[1], 63, 62.7, descriptionCharacter[0],
                        Arrays.asList(
                                new Movie(titleMovie[0], date[0], 5),
                                new Movie(titleMovie[1], date[1], 3))),
                new Character(3L, nameCharater[2], 31, 58.3, descriptionCharacter[0],
                        Arrays.asList(
                                new Movie(titleMovie[0], date[0], 5),
                                new Movie(titleMovie[1], date[1], 3))),
                new Character(4L, nameCharater[3], 65, 68.7, descriptionCharacter[1],
                        Arrays.asList(
                                new Movie(titleMovie[2], date[0], 4))),
                new Character(5L, nameCharater[4], 52, 64.7, descriptionCharacter[1],
                        Arrays.asList(
                                new Movie(titleMovie[2], date[2], 4),
                                new Movie(titleMovie[3], date[3], 5))),
                new Character(6L, nameCharater[5], 36, 72.5, descriptionCharacter[2],
                        Arrays.asList(
                                new Movie(titleMovie[3], date[3], 5))),
                new Character(7L, nameCharater[6], 38, 82.5, descriptionCharacter[2],
                        Arrays.asList(
                                new Movie(titleMovie[3], date[3], 5)))
        );
    }

    public static List<Movie> addMovie() {
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
}
