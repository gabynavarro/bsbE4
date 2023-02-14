package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);

    List<Movie> findByGenderName(String gender);
    @Query("SELECT m FROM Movie m WHERE m.date BETWEEN :of AND :to")
    List<Movie> findByDate(LocalDate of, LocalDate to);
    @Query("SELECT m FROM Movie m WHERE m.score BETWEEN :of AND :to")
    List<Movie> findByScore(int of, int to);
}
