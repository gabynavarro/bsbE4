package com.bsb.ejercicio.service.impl;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ElementNotFound;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.model.mappers.MovieMapper;
import com.bsb.ejercicio.model.request.MovieRequest;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import com.bsb.ejercicio.repository.CharacterRepository;
import com.bsb.ejercicio.repository.GenderRepository;
import com.bsb.ejercicio.repository.MovieRepository;
import com.bsb.ejercicio.service.IMovieService;
import com.bsb.ejercicio.validations.Validations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MovieServiceImpl implements IMovieService {
    private static final String ERROR_NOT_FOUND = "An error occurred in the process";
    private static final String ERROR_NOT_VALIDATE = "The data entered contains erroneous information";
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private MovieMapper movieMapper;

    private List<MovieResponse> converTo(List<Movie> list) {       //borrar
        return list.stream()
                .map(m -> movieMapper.toMovieResponse(m))
                .collect(Collectors.toList());
    }

    @Override
    public MovieResponse findTitle(String title) throws ErrorProcessException {
        if (title == null)
            throw new NullPointerException("Movie name can't be null or contains invalid characters");
        try {
            Movie m = movieRepository.findByTitle(title)
                    .orElseThrow(() -> new ElementNotFound("The title is not found in the database"));
            return movieMapper.toMovieResponse(m);
        } catch (RuntimeException | ElementNotFound e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public List<MovieResponse> getAll() throws ErrorProcessException {
        try {
            return converTo(movieRepository.findAll());
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public List<MovieResponse> findByGender(String gender) throws ErrorProcessException {
        if (gender == null && !Validations.validationString(gender))
            throw new NumberFormatException("Movie name gender can't be null or contains invalid characters");

        try {
            return converTo(movieRepository.findByGenderName(gender));
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public List<MovieResponse> findByDate(LocalDate from, LocalDate to) throws ErrorProcessException {
        try {
            List<Movie> listMovie = movieRepository.findByDate(from, to);
            return converTo(listMovie);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public List<MovieResponse> findByScore(int from, int to) throws ErrorProcessException {
        try {
            return converTo(movieRepository.findByScore(from, to));

        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public MovieResponse movieCreate(MovieRequest movie) throws BadRequestException, ErrorProcessException {
        List<Character> listCharacter = new ArrayList<>();
        if (!Validations.validateMovieEntity(movie)) {
            throw new BadRequestException(ERROR_NOT_VALIDATE);
        }
        if (movieRepository.findByTitle(movie.getTitle()).orElse(null) != null) {
            throw new BadRequestException("Movie exist!");
        }
        try {
            Movie m = movieMapper.toEntity(movie);
            for (String c : movie.getIdCharacters()) {
                Character character = characterRepository.findById(Long.valueOf(c)).orElse(null);
                if (character != null) {
                    listCharacter.add(character);
                }
            }
            if (listCharacter.isEmpty()) {
                m.setCharacter(new ArrayList<>());
            } else m.setCharacter(listCharacter);
            m.setGender(genderRepository.findById(movie.getGender()).orElse(null));
            return movieMapper.toMovieResponse(movieRepository.save(m));
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    public MovieResponse findById(Long id) throws ErrorProcessException {
        try {
            return movieMapper.toMovieResponse(movieRepository.findById(id).orElse(null));

        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public MovieResponse update(Long id, MovieRequest movie) throws ErrorProcessException {
        if (!Validations.validateMovieEntity(movie))
            throw new RuntimeException(ERROR_NOT_VALIDATE);
        try {
            Movie m = movieRepository.findById(id).orElse(null);
            if (m != null) {
                m.setTitle(movie.getTitle());
                m.setDate(movie.getDate());
                m.setScore(movie.getScore());
                log.info("Movie modificada: "+ m.getTitle().toUpperCase());
                return movieMapper.toMovieResponse(movieRepository.save(m));
            } else throw new NullPointerException("The id entered is incorrect or deleted");

        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws ElementNotFound, ErrorProcessException {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ElementNotFound("The id " + id + " is not found in the database"));
        if (movie.isSoftDeleted()) {
            throw new ElementNotFound("movie not found");
        }
        try {
            movie.setSoftDeleted(true);
            movieRepository.save(movie);
            log.info("La pelicula "+movie.getTitle().toUpperCase() + " fue eliminada");
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + " " + e.getMessage());
        }
    }
}
