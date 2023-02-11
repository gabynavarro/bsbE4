package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Character;

import java.util.List;

public interface CharacterRepository {
    List<Character> findName(String name);

    List<Character>  findCharAll();

    List<Character> findByAge(int age);

    List<Character> findByRangeAge(int from, int to);

    List<Character> characterCreate(Character character);

    Character findById(Long id);


}
