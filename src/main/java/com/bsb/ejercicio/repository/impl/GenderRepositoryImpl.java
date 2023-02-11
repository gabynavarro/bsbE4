package com.bsb.ejercicio.repository.impl;

import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.repository.GenderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class GenderRepositoryImpl implements GenderRepository{
    private   List<Gender> addGender() {
        return Arrays.asList(new Gender(1L, "Suspenso"),
                new Gender(2L, "Terror"),
                new Gender(3L, "Suspenso"),
                new Gender(4L, "Comedia")
        );
    }
    @Override
    public List<Gender> getGenderAll() {
       return  new ArrayList<>(this.addGender());
    }

    @Override
    public List<Gender> genderCreate(Gender gender) {
        List<Gender> genders = getGenderAll();
        gender.setId((long) (genders.size() + 1));
        genders.add(genders.size(), gender);
        return genders;
    }

    @Override
    public Gender findById(Long id) {
        List<Gender> genders = this.addGender();
        return genders.stream().filter(g-> Objects.equals(g.getId(), id)).findFirst().orElse(null);
    }

}
