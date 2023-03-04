package com.bsb.ejercicio.service.impl;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ElementNotFound;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.exception.NotFoundException;
import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.model.mappers.CharacterMapper;
import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;
import com.bsb.ejercicio.repository.CharacterRepository;
import com.bsb.ejercicio.repository.MovieRepository;
import com.bsb.ejercicio.service.ICharacterService;
import com.bsb.ejercicio.validations.Validations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CharacterServiceImpl implements ICharacterService {
    private final String ERROR_NOT_FOUND = "An error occurred in the process";
    private final String ERROR_NOT_VALIDATE = "The data entered contains erroneous information";
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private MovieRepository movieRepository;

    private List<CharacterResponse> converTo(List<Character> list) {       //borrar
        return list.stream()
                .map(c -> characterMapper.toResponse(c))
                .collect(Collectors.toList());
    }

    @Override
    public CharacterResponse findName(String name) throws ErrorProcessException {
        if (name == null || Validations.validationString(name))
            throw new NullPointerException("Character name can't be null or contains invalid characters");
        try {
            Character character = characterRepository.findByName(name)
                    .orElseThrow(() -> new ElementNotFound("The name " + name + " is not found in the database"));
            return characterMapper.toResponse(character);

        } catch (RuntimeException | ElementNotFound e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public List<CharacterResponse> getAll() throws ErrorProcessException {
        try {
            return this.converTo(characterRepository.findAll());
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public List<CharacterResponse> findByAge(int age) throws ErrorProcessException {
        if (!Validations.validateAgeM0(age) || !Validations.validateAgem0(age)) {
            throw new NumberFormatException("The number entered is not valid");
        }
        try {
            return this.converTo(characterRepository.findByAge(age));

        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public List<CharacterResponse> findByRangeAge(int from, int to) throws ErrorProcessException {
        try {
            List<Character> characters = characterRepository.findByRangeAge(from, to);
            return this.converTo(characters);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public CharacterResponse characterCreate(CharacterRequest character) throws ErrorProcessException, BadRequestException {
        List<Movie> listMovie = new ArrayList<>();
        if (!characterRepository.findByName(character.getName()).isEmpty()) {
            throw new BadRequestException("Character exist!");
        }
        if (Validations.validateCharacterEntity(character))
            throw new BadRequestException(ERROR_NOT_VALIDATE);
        try {

            Character c = characterMapper.toCharacter(character);
            for (String m : character.getListMovies()) {
                Movie movie = movieRepository.findById(Long.valueOf(m)).orElse(null);
                if (movie != null) {
                    listMovie.add(movie);
                }
            }
            if (listMovie.isEmpty()) {
                c.setListMovie(new ArrayList<>());
            } else c.setListMovie(listMovie);
            Character ch=characterRepository.save(c);
            return characterMapper.toResponse(c);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public CharacterResponse findById(Long id) throws ErrorProcessException {
        try {
            return characterMapper.toResponse(characterRepository.findById(id).orElse(null));
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public CharacterResponse update(Long id, CharacterRequest character) throws ErrorProcessException, BadRequestException {
        if (Validations.validateCharacterEntity(character))
            throw new BadRequestException(ERROR_NOT_VALIDATE);
        try {
            Character c = characterRepository.findById(id).orElse(null);
            if (c != null) {
                c.setName(character.getName());
                c.setAge(character.getAge());
                c.setHistory(character.getHistory());
                c.setWeight(character.getWeight());
                log.info("Caracter modificated: "+ c.getName().toUpperCase());
                return characterMapper.toResponse(characterRepository.save(c));
            } else throw new NullPointerException("The id entered is incorrect or deleted");
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }
    @Override
    @Transactional
    public void delete(Long id) throws ElementNotFound, ErrorProcessException {
        Character character = characterRepository.findById(id).orElseThrow(() -> new ElementNotFound("The id " + id + " is not found in the database"));
        if(character.isSoftDeleted()){
            throw new ElementNotFound("character not found");
        }
        try {
            character.setSoftDeleted(true);
            characterRepository.save(character);
            log.info("El character "+character.getName().toUpperCase()+ " fue eliminado");
        }catch (RuntimeException e){
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }

    }
}

