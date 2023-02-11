package com.bsb.ejercicio.validations;

import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.request.MovieRequest;


public class Validations {

    public static boolean validationString(String text) {
        return text.matches("^[a-zA-Z]+$");
    }
    public static String validationStringSpacing(String text) {
        return text.replaceAll("\\s","");
    }

    public static boolean validateAgeM0(int age) {
        return age > 0;
    }

    public static boolean validateAgem0(int age) {
        return age < 120;
    }
    public static boolean validateMovieEntity(MovieRequest movie){
        if(validationString(movie.getTitle())) return true;
        if(movie.getScore()<0&&movie.getScore()>5) return true;
        return false;
    } public static boolean validateCharacterEntity(CharacterRequest character){
        if(!validationString(character.getName())) return true;
        if(character.getAge()<0&&character.getAge()>125) return true;
        return false;
    }
}
