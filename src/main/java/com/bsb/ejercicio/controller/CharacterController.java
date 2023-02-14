package com.bsb.ejercicio.controller;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;
import com.bsb.ejercicio.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character")
public class CharacterController {
    @Autowired
    private ICharacterService characterService;

    @GetMapping("/{name}")
    public ResponseEntity<CharacterResponse> getCharacterName(@PathVariable String name) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findName(name));
    }

    @GetMapping
    public ResponseEntity<List<CharacterResponse>> getCharacter() throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAll());
    }

    @GetMapping(value = "/age/{age}")
    public ResponseEntity<List<CharacterResponse>> getCharacterAge(@PathVariable int age) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findByAge(age));
    }

    @GetMapping(value = "age")
    public ResponseEntity<List<CharacterResponse>> getCharacterRangeAge(
            @RequestParam int from, @RequestParam int to) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findByRangeAge(from, to));
    }

    @PostMapping
    public ResponseEntity<CharacterResponse> characterAdd(
            @RequestBody CharacterRequest character) throws BadRequestException, ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.characterCreate(character));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponse> update(
            @RequestBody CharacterRequest character,
            @PathVariable Long id) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.update(id, character));
    }
}
