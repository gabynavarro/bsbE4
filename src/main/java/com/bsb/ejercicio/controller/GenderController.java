package com.bsb.ejercicio.controller;

import com.bsb.ejercicio.model.entity.Gender;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.GenderResponse;
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
    public  ResponseEntity<List<GenderResponse>> getGender() {
        return  ResponseEntity.status(HttpStatus.OK).body(genderService.getAll());
    }
    @PostMapping
    public ResponseEntity<List<GenderResponse>> genderAdd(
            @RequestBody GenderRequest gender) {
        return ResponseEntity.status(HttpStatus.OK).body(genderService.genderCreate(gender));
    }
    @GetMapping(value = "{id}")
    public  ResponseEntity<GenderResponse> getGender(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(genderService.findById(id));
    }
}
