package com.bsb.ejercicio.controller;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.Gender.GenderResponse;
import com.bsb.ejercicio.service.IGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    private IGenderService genderService;
    @GetMapping
    public  ResponseEntity<List<GenderResponse>> getAll() throws ErrorProcessException {
        return  ResponseEntity.status(HttpStatus.OK).body(genderService.getAll());
    }
    @PostMapping
    public ResponseEntity<GenderResponse> genderAdd(
            @RequestBody GenderRequest gender) throws BadRequestException, ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(genderService.genderCreate(gender));
    }
    @GetMapping(value = "{id}")
    public  ResponseEntity<GenderResponse> getGenderId(@PathVariable Long id) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(genderService.findById(id));
    }
}
