package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Gender;

import java.util.List;

public interface GenderRepository {
    List<Gender> getGenderAll();

    List<Gender> genderCreate(Gender gender);

    Gender findById(Long id);
}
