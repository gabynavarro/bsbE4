package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String title);

    List<Movie> findByGender(String gender);

    //List<Movie> findByDate(LocalDate from, LocalDate to);

 //   List<Movie> findByScore(int from, int to);


  //  Movie findById(Long id);

}
