package com.bsb.ejercicio.model.response.character;


import lombok.*;
import com.bsb.ejercicio.model.entity.Character;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class CharacterResponse {
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<CharacterMovieResponse> listMovie;

    public static  Character toEnty(CharacterResponse c){
        return  Character.builder()
                .id(c.id)
                .name(c.name)
                .age(c.age)
                .weight(c.weight)
                .history(c.history)
                .listMovie(new ArrayList<>())
                .build();
    }

}
