package com.bsb.ejercicio.service;

import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;

import java.util.List;

public interface ICharacterService {
      List<CharacterResponse> findName(String name);
      List<CharacterResponse> getAll();
      List<CharacterResponse> findByAge(int age);
      List<CharacterResponse> findByRangeAge(int from, int to);
      List<CharacterResponse> characterCreate(CharacterRequest character);
      CharacterResponse findById(Long id);
      CharacterResponse update(Long id, CharacterRequest character);
}
