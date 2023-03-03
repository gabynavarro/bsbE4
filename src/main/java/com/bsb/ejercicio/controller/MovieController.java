package com.bsb.ejercicio.controller;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ElementNotFound;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.exception.NotFoundException;
import com.bsb.ejercicio.model.request.MovieRequest;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import com.bsb.ejercicio.service.IMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movie")
@Api(value = "Movie Controller", tags = "Actions allowed for movies")
public class MovieController {
    @Autowired
    private IMovieService movieService;
    @GetMapping("title")
    @ApiOperation(value = "find by title movie", notes = "Return movie type response" )
    public ResponseEntity<MovieResponse> getMovieTitle(@RequestParam(name = "title", required = false) String title) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findTitle(title));
    }

    @GetMapping
    @ApiOperation(value = "find by all movies", notes = "Return list movies type responses" )
    public ResponseEntity<List<MovieResponse>> getMovieAll() throws NotFoundException, ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAll());
    }

    @GetMapping("{gender}")
    @ApiOperation(value = "find movie by id gender", notes = "Return list movies by gender type response" )
    public ResponseEntity<List<MovieResponse>> getMovieGender(@PathVariable String gender) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findByGender(gender));
    }

    @GetMapping("date")
    @ApiOperation(value = "find movies by range dates", notes = "Return list movies type response" )
    public ResponseEntity<List<MovieResponse>> getMovieDate(
            @RequestParam(value = "from", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam(value = "to", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to
    ) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findByDate(from, to));
    }

    @GetMapping("score")
    @ApiOperation(value = "find movies by range score", notes = "Return list movies type response" )
    public ResponseEntity<List<MovieResponse>> getMovieGender(
            @RequestParam(value = "from", required = true) int from,
            @RequestParam(value = "to", required = true) int to
    ) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findByScore(from, to));
    }

    @PostMapping()
    @ApiOperation(value = "create new movie", notes = "Return a movie type response" )
    public ResponseEntity<MovieResponse> movieAdd(
            @RequestBody MovieRequest movie) throws BadRequestException, ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.movieCreate(movie));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "find by id and update movie", notes = "returns modified movie ty response" )
    public ResponseEntity<MovieResponse> update(
            @RequestBody MovieRequest movie,
            @PathVariable Long id) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.update(id, movie));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "find by id and delete movie", notes = "does not return movies or response" )
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ErrorProcessException, ElementNotFound {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
