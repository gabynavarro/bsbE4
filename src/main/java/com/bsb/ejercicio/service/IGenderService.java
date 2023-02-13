package com.bsb.ejercicio.service;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.GenderResponse;

import java.util.List;

public interface IGenderService {
    List<GenderResponse> getAll();
    GenderResponse genderCreate(GenderRequest gender) throws BadRequestException;
    GenderResponse findById(Long id);
    GenderResponse update(Long id, GenderRequest gender);
}
