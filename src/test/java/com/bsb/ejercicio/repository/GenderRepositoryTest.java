package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.datos.DatosDummy;
import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.validations.Validations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class GenderRepositoryTest {
    @Autowired
    private GenderRepository genderRepository;
    @BeforeEach
    void setUp() {
        List <Gender> list = DatosDummy.addGender();
        list.forEach((c) -> genderRepository.save(c));
    }

    @AfterEach
    void tearDown() {
        genderRepository.deleteAll();
    }

    @Test
    void toLookFor() {
        String gender="Terror";
        //WHEN
        Gender g = genderRepository.findByName(gender).orElse(null);        //THEN
        assertThat(g!=null).isTrue();
        assertThat(g.getName())
                .isEqualTo(gender);
        assertThat(Validations.validationString(g.getName())).isTrue();
    }


    @Test
    void findByName() {
        //GIVEN
        String gender="Terror";
        //WHEN
        Gender g = genderRepository.findByName(gender).orElse(null);
        //THEN
        assertThat(g!=null).isTrue();
        assertThat(g.getName())
                .isEqualTo(gender);
        assertThat(Validations.validationString(g.getName())).isTrue();
      }
}