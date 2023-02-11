package com.bsb.ejercicio.model.mappers;

import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;
import com.bsb.ejercicio.model.response.movie.MovieCharacterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CharacterMapper {
    @Autowired
    private MovieMapper movieMapper;

    public CharacterResponse toResponse (Character character){
    return CharacterResponse.builder()
            .id(character.getId())
            .name(character.getName())
            .age(character.getAge())
            .history(character.getHistory())
            .weight(character.getWeight())
           .listMovie(character.getListMovie().stream()
                    .map(movieMapper::toResponse)
                    .collect(Collectors.toList())
            )
            .build();
    }
    public Character toCharacter (CharacterRequest character){
        return Character.builder()
                .name(character.getName())
                .age(character.getAge())
                .history(character.getHistory())
                .weight(character.getWeight())
                .build();
    }
    public MovieCharacterResponse toMovieCharacter (Character character){
        return MovieCharacterResponse.builder()
                .name(character.getName())
                .build();
    }
}
