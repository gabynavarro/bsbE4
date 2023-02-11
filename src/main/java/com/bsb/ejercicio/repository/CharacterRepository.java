package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
 //   @Query("select c from Character c where c.name = :name")
    List<Character> findByName(String name);

  //  @Query("select c from Character c where c.age = :age")
    List<Character> findByAge(int age);

    @Query("SELECT c FROM Character c WHERE c.age BETWEEN :since AND :until")
    List<Character> findByRangeAge( int since, int until);
}
