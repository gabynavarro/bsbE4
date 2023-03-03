package com.bsb.ejercicio.controller;

import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ElementNotFound;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.exception.NotFoundException;
import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;
import com.bsb.ejercicio.service.ICharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character")
@Api(value = "Character Controller", tags = "actions allowed for characters")
public class CharacterController {
    @Autowired
    private ICharacterService characterService;

    @GetMapping("/{name}")
    @ApiOperation(value = "search by character name", notes = "Return a character type response" )
    public ResponseEntity<CharacterResponse> getCharacterName(@PathVariable String name) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findName(name));
    }

    @GetMapping
    @ApiOperation(value = "search all characters ", notes = "Return a list characters type response" )
    public ResponseEntity<List<CharacterResponse>> getCharacter() throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAll());
    }

    @GetMapping(value = "/age/{age}")
    @ApiOperation(value = "search characters by age", notes = "Return a list characters type response" )
    public ResponseEntity<List<CharacterResponse>> getCharacterAge(@PathVariable int age) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findByAge(age));
    }

    @GetMapping(value = "age")
    @ApiOperation(value = "search characters range age", notes = "Return a list characters type response" )
    public ResponseEntity<List<CharacterResponse>> getCharacterRangeAge(
            @RequestParam int from, @RequestParam int to) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findByRangeAge(from, to));
    }

    @PostMapping
    @ApiOperation(value = "create a character", notes = "Return character type response" )
    public ResponseEntity<CharacterResponse> characterAdd(
            @RequestBody CharacterRequest character) throws BadRequestException, ErrorProcessException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.characterCreate(character));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update by id a character", notes = "returns modified character" )
    public ResponseEntity<CharacterResponse> update(
            @RequestBody CharacterRequest character,
            @PathVariable Long id) throws ErrorProcessException, BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.update(id, character));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete a character", notes = "does not have a return, returns only state" )
    public ResponseEntity<Void> deletedCharacter(@PathVariable Long id) throws NotFoundException, ErrorProcessException, ElementNotFound {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
