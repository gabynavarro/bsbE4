package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Gender;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
    @Query("select g from Gender g where g.name = :name ")
    Gender toLookFor(String name);
    Optional<Gender> findByName(String name);

}
