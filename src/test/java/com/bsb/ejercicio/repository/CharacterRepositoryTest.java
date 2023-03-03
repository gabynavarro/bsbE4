package com.bsb.ejercicio.repository;

import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.datos.DatosDummy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;


import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class CharacterRepositoryTest {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MovieRepository movieRepository;
    //construccion de dummy
    @BeforeEach
    void setUp() {
        List<Character> list = DatosDummy.addCharacter();
        list.forEach((c) -> characterRepository.save(c));

    }

    @AfterEach
    void tearDown() {
       // characterRepository.deleteAll();
    }

    @Test
    void findByName() {
        //GIVEN
        /* Contecto  de persistencia*/

        String test_name = "Paul-Rudd";
        //WHEN
        /* Cuando lo vamos usar aa  nuestro codigo */
        Character c = characterRepository.findByName(test_name).orElse(null);

        //THEN
        /*Lo que queremos probar*/
        assertThat(c!=null).isTrue();
        assertThat(c.getName())
                .isEqualTo(test_name);
    }

    @Test
    void findByAge() {
        //GIVEN
        int age = 31;
        //WHEN
        List<Character> c = characterRepository.findByAge(age);
        //THEN
        assertThat(c.size()>0).isTrue();
        assertThat(age==c.get(0).getAge());
    }

    @Test
    void findByRangeAge() {
        //GIVEN
        int from = 31;
        int to=52;
        //WHEN
        List<Character> c = characterRepository.findByRangeAge(from,to);
        //THEN
        assertThat(c.size()>0).isTrue();
        assertThat(from<=c.get(0).getAge()&&to>=c.get(0).getAge());
    }
}