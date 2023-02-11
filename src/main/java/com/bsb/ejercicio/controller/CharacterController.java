package com.bsb.ejercicio.controller;

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
    @GetMapping("name")
    public ResponseEntity<List<CharacterResponse>> getCharacterName(@RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findName(name));
    }

    @GetMapping
    public ResponseEntity<List<CharacterResponse>> getCharacter() {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAll());
    }

    @GetMapping(value = "/age/{age}")
    public ResponseEntity<List<CharacterResponse>> getCharacterAge(@PathVariable int age) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findByAge(age));
    }

    @GetMapping(value = "age")
    public ResponseEntity<List<CharacterResponse>> getCharacterRangeAge(
            @RequestParam int from, @RequestParam int to) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findByRangeAge(from, to));
    }

    @PostMapping
    public ResponseEntity<List<CharacterResponse>> characterAdd(
            @RequestBody CharacterRequest character) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.characterCreate(character));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponse> update(
            @RequestBody CharacterRequest character,
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.update(id, character));
    }
}
