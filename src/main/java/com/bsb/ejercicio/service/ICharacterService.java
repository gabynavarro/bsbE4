package com.bsb.ejercicio.service;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ElementNotFound;
import com.bsb.ejercicio.exception.ErrorProcessException;

import com.bsb.ejercicio.exception.NotFoundException;
import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;

import java.util.List;

public interface ICharacterService {
      CharacterResponse findName(String name) throws ErrorProcessException;
      List<CharacterResponse> getAll() throws ErrorProcessException;
      List<CharacterResponse> findByAge(int age) throws ErrorProcessException;
      List<CharacterResponse> findByRangeAge(int from, int to) throws ErrorProcessException;
      CharacterResponse characterCreate(CharacterRequest character) throws ErrorProcessException, BadRequestException;
      CharacterResponse findById(Long id) throws ErrorProcessException;
      CharacterResponse update(Long id, CharacterRequest character) throws ErrorProcessException, BadRequestException;
      void delete(Long id) throws NotFoundException, ElementNotFound, ErrorProcessException;
}
