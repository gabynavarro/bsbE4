package com.bsb.ejercicio.model.request;

import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class MovieRequest {

    private String title;
    private LocalDate date;
    private int score;
    private List<String> idCharacters;
    private long gender;
}
