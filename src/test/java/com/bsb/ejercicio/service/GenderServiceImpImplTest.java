package com.bsb.ejercicio.service;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.NotFoundException;
import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.mappers.GenderMapper;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.Gender.GenderResponse;
import com.bsb.ejercicio.repository.GenderRepository;
import com.bsb.ejercicio.service.impl.GenderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class GenderServiceImpImplTest {
    @InjectMocks
    private GenderServiceImpl genderService;

    @Mock
    private GenderRepository genderRepository;

    @InjectMocks
    private GenderMapper genderMapper;

    private Gender gender;
    private GenderRequest request;
    private GenderResponse response;

    @BeforeEach
        // Esto se inicia primero por la anotacion
    void setUp(){
        /*
         * Esto es necesario para que Mockito pueda instanciar los Mock Objects
         * e Inyectar en el objeto GenderServiceImpl
         */
        MockitoAnnotations.openMocks(this);

        gender = new Gender();
        gender.setId(1L);
        gender.setName("Romance");
        gender.setMovieOrSeriesLis(new ArrayList<>());
        request = new GenderRequest();
        request.setName("Romance");

    }

    @Test
    void testGetGenderByName() throws NotFoundException {
        // Simular la llamada al método toLookFor usando el objeto genderRepository simulado
        when(genderRepository.toLookFor(anyString())).thenReturn(gender);
        GenderResponse response = genderMapper.toResponse(gender);
        assertNotNull(response);
        assertEquals("Romance", response.getName());
    }


    @Test
    void altaGenero() throws BadRequestException {
        gender = genderMapper.toEntity(request);

        when(genderRepository.findByName(anyString())).thenReturn(null);
        when(genderRepository.save(any(Gender.class))).thenReturn(gender);
        response = genderMapper.toResponse(gender);

        assertNotNull(response);
        assertEquals(gender.getName(), response.getName());

    }
}
