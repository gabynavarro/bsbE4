package com.bsb.ejercicio.controller;

import com.bsb.ejercicio.model.request.MovieRequest;
import com.bsb.ejercicio.model.response.movie.MovieResponse;
import com.bsb.ejercicio.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private IMovieService movieService;
    @GetMapping("title")
    public ResponseEntity<List<MovieResponse>> getMovieTitle(@RequestParam(name = "title", required = false) String title) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findTitle(title));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovieAll() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAll());
    }

    @GetMapping("{gender}")
    public ResponseEntity<List<MovieResponse>> getMovieGender(@PathVariable String gender) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findByGender(gender));
    }

    @GetMapping("date")
    public ResponseEntity<List<MovieResponse>> getMovieGender(
            @RequestParam(value = "from", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam(value = "to", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findByDate(from, to));
    }

    @GetMapping("score")
    public ResponseEntity<List<MovieResponse>> getMovieGender(
            @RequestParam(value = "from", required = true) int from,
            @RequestParam(value = "to", required = true) int to
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findByScore(from, to));
    }

    @PostMapping()
    public ResponseEntity<List<MovieResponse>> movieAdd(
            @RequestBody MovieRequest movie) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.movieCreate(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(
            @RequestBody MovieRequest movie,
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.update(id, movie));
    }
}
