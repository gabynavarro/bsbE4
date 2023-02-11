package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Gender;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GenderRepository {
    List<Gender> getGenderAll();

    List<Gender> genderCreate(Gender gender);

    Gender findById(Long id);
}
