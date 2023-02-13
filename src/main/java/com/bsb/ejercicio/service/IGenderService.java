package com.bsb.ejercicio.service;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.GenderResponse;

import java.util.List;

public interface IGenderService {
    List<GenderResponse> getAll() throws ErrorProcessException;
    GenderResponse genderCreate(GenderRequest gender) throws BadRequestException, ErrorProcessException;
    GenderResponse findById(Long id) throws ErrorProcessException;
    GenderResponse update(Long id, GenderRequest gender) throws ErrorProcessException;
}
