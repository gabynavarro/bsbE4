package com.bsb.ejercicio.repository;
import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.datos.DatosDummy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
@DataJpaTest
class CharacterRepositoryTest {
    @Autowired
    private CharacterRepository characterRepository;
    //construccion de dummy
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByName() {
        //GIVEN
        /* Contecto  de persistencia*/
        List<Character> list= DatosDummy.addCharacter();
        list.forEach((c)-> characterRepository.save(c) );
        String test_name="gabriel";

        //WHEN
        /* Cuando lo vamos usar aa  nuestro codigo */
        Optional<Character> c=characterRepository.findByName(list.get(0).getName());

        //THEN
        /*Lo que queremos probar*/
        assertThat(c.isPresent())
                .isTrue();
        assertThat(c.get().getName())
                .isEqualTo(test_name);
    }

    @Test
    void findByAge() {
    }

    @Test
    void findByRangeAge() {
    }
}