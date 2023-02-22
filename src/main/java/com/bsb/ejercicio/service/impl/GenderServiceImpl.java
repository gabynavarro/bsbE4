package com.bsb.ejercicio.service.impl;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.mappers.GenderMapper;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.Gender.GenderResponse;
import com.bsb.ejercicio.repository.GenderRepository;
import com.bsb.ejercicio.service.IGenderService;
import com.bsb.ejercicio.validations.Validations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GenderServiceImpl implements IGenderService {
    private final String ERROR_NOT_FOUND = "An error occurred in the process";
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private GenderMapper genderMapper;


    private List<GenderResponse> converTo(List<Gender> list) {       //borrar
        return list.stream()
                .map(g -> genderMapper.toResponse(g))
                .collect(Collectors.toList());
    }

    @Override
    public List<GenderResponse> getAll() throws ErrorProcessException {
        try {
            return converTo(genderRepository.findAll());
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND +" "+e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenderResponse genderCreate(GenderRequest gender) throws BadRequestException, ErrorProcessException {
        if (!Validations.validationString(gender.getName().replaceAll("\\s", "")))
            throw new BadRequestException("The gender name is not valid");
        if (!genderRepository.findByName(gender.getName()).isEmpty())
            throw new BadRequestException("Gender exist!");

        try {
            return genderMapper.toResponse(genderRepository.save(genderMapper.toEntity(gender)));
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND +" "+e.getMessage());
        }
    }

    @Override
    public GenderResponse findById(Long id) throws ErrorProcessException {
        try {
            return genderMapper.toResponse(genderRepository.findById(id).orElse(null));
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND +" "+e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenderResponse update(Long id, GenderRequest gender) throws ErrorProcessException {
        if (!Validations.validationString(gender.getName()))
            throw new RuntimeException("He entered an invalid name");
        try {
            Gender g = genderRepository.findById(id).orElse(null);
            if (g != null) {
                g.setName(gender.getName());
                genderRepository.save(g);
                log.info("Se modifico gender: "+g.getName().toUpperCase());
                return genderMapper.toResponse(g);
            } else throw new NullPointerException("The id entered is incorrect or deleted");
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND +" "+e.getMessage());
        }
    }
}

