package com.bsb.ejercicio.service;

import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.GenderResponse;

import java.util.List;

public interface IGenderService {
    List<GenderResponse> getAll();
    List<GenderResponse> genderCreate(GenderRequest gender);
    GenderResponse findById(Long id);
    GenderResponse update(Long id, GenderRequest gender);
}
