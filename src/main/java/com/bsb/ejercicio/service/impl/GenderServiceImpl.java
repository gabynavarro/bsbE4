package com.bsb.ejercicio.service.impl;

import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.mappers.GenderMapper;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.GenderResponse;
import com.bsb.ejercicio.repository.GenderRepository;
import com.bsb.ejercicio.service.IGenderService;
import com.bsb.ejercicio.validations.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenderServiceImpl implements IGenderService {
    private  final String ERROR_NOT_FOUND = "An error occurred in the process";
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private GenderMapper genderMapper;
    @Override
    public List<GenderResponse> getAll() {
        try {
            return converTo(genderRepository.getGenderAll());
        } catch (Exception e) {
            throw new RuntimeException(ERROR_NOT_FOUND);
        }
    }
    private List<GenderResponse> converTo(List<Gender> list) {       //borrar
        return list.stream()
                .map(g -> genderMapper.toResponse(g))
                .collect(Collectors.toList());
    }
    @Override
    public List<GenderResponse> genderCreate(GenderRequest gender) {
        try {
            Gender g=genderMapper.toEntity(gender);
            return converTo(genderRepository.genderCreate(g));
        } catch (Exception e) {
            throw new RuntimeException(ERROR_NOT_FOUND);
        }
    }

    @Override
    public GenderResponse findById(Long id) {
        try {
            return  genderMapper.toResponse(genderRepository.findById(id));
        } catch (Exception e) {
            throw new RuntimeException(ERROR_NOT_FOUND);
        }
    }

    @Override
    public GenderResponse update(Long id, GenderRequest gender) {
        try {
            Gender m = genderRepository.findById(id);
            if (!Validations.validationString(gender.getName()))
                throw new RuntimeException("He entered an invalid name");
            if (m != null) {
                m.setName(gender.getName());
                return  genderMapper.toResponse(m);
            } else throw new NullPointerException("The id entered is incorrect or deleted");
        } catch (Exception e) {
            throw new RuntimeException(ERROR_NOT_FOUND);
        }
    }
}

