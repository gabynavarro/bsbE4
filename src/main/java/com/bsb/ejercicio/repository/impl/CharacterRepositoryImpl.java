package com.bsb.ejercicio.repository.impl;

import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.repository.CharacterRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository {
    private  final String[] nameCharater = {"Robert-Downey", "Chris-Evans", "Chris-Hemsworth",
            "Paul-Rudd", "Michael-Douglas",
            "Guy-Pearce", "Don-Cheadle"
    };
    private  final String[] descriptionCharacter = {"Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity...",
            "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity...",
            "Armed with a super-suit with the astonishing ability to shrink in scale but increase in strength, cat burglar Scott Lang must embrace his inner hero and help...",
            "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution."
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


    private  List<Character> addCharacter() {
        return Arrays.asList(
                new Character(1L, nameCharater[0], 63, 65.5, descriptionCharacter[0],
                        Arrays.asList(
                                new Movie(titleMovie[0], date[0], 5),
                                new Movie(titleMovie[1], date[1], 3))
                ),
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
    @Override
    public List<Character> findName(String name) {
        List<Character> characters = this.addCharacter();
        return characters.stream().filter(c -> c.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    @Override
    public List<Character> findCharAll() {
        List<Character> listChar=this.addCharacter();
        return listChar;
    }

    @Override
    public List<Character> findByAge(int age) {
        List<Character> characters = findCharAll();
        return characters.stream().filter(c -> c.getAge() == age).collect(Collectors.toList());
    }

    @Override
    public List<Character> findByRangeAge(int from, int to) {
        List<Character> characters = this.addCharacter();
        return characters.stream()
                .filter(c -> c.getAge() >= from && c.getAge() <= to)
                .collect(Collectors.toList());
    }

    @Override
    public List<Character> characterCreate(Character character) {
        List<Character> characters = new ArrayList<>(findCharAll());
        character.setId((long) (characters.size() + 1));
        characters.add(characters.size(),character);
        return characters;
    }
    @Override
    public Character findById(Long id) {
        List<Character> characters = this.addCharacter();
        return characters.stream().filter(g -> Objects.equals(g.getId(), id)).findFirst().orElse(null);
    }
}
