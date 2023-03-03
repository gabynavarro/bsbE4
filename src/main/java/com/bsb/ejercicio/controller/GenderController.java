package com.bsb.ejercicio.controller;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.request.GenderRequest;
import com.bsb.ejercicio.model.response.Gender.GenderResponse;
import com.bsb.ejercicio.service.IGenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gender")
@Api(value = "Gender Controller", tags = "Actions allowed for genders")
public class GenderController {
    @Autowired
    private IGenderService genderService;
    @GetMapping
    @ApiOperation(value = "search all genders ", notes = "Return a list genders type response" )

    public  ResponseEntity<List<GenderResponse>> getAll() throws ErrorProcessException {
        return  ResponseEntity.status(HttpStatus.OK).body(genderService.getAll());
    }
    @PostMapping
    @ApiOperation(value = "create a gender", notes = "Return gender type response" )
    public ResponseEntity<GenderResponse> genderAdd(
            @RequestBody GenderRequest gender) throws BadRequestException, ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(genderService.genderCreate(gender));
    }
    @GetMapping(value = "{id}")
    @ApiOperation(value = "find by id gender", notes = "Return gender type response" )
    public  ResponseEntity<GenderResponse> getGenderId(@PathVariable Long id) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(genderService.findById(id));
    }
}
