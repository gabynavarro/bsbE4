package com.bsb.ejercicio.datos;

import com.bsb.ejercicio.model.response.character.CharacterResponse;
import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Movie;
import java.util.ArrayList;

public class CharacterServiceDataUtils {
    public static CharacterResponse getCharacterResponse (){
        CharacterResponse response = new CharacterResponse();

        response.setId(1L);
        response.setName(DatosDummy.nameCharater[0]);
        response.setAge(43);
        response.setWeight(62.3);
        response.setHistory(DatosDummy.descriptionCharacter[0]);
        response.setListMovie(new ArrayList<>());

        return response;
    }

    public static Character getCharacter (){
        Character character = new Character();
        character.setId(Long.valueOf(1));
        character.setName(DatosDummy.nameCharater[0]);
        character.setAge(43);
        character.setWeight(62.3);
        character.setHistory(DatosDummy.descriptionCharacter[0]);
        character.setListMovie(new ArrayList<>());
        return character;
    }

    public static Movie getMovie(){
        Movie movie = new Movie();



        return movie;
    }
}
