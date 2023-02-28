package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findByName(String name);
    List<Character> findByAge(int age);

    @Query("SELECT c FROM Character c WHERE c.age BETWEEN :of AND :to")
    List<Character> findByRangeAge( int of, int to);
}
