package com.bsb.ejercicio.model.response.character;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.bsb.ejercicio.model.entity.Character;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CharacterResponse", description = "Response with the requested character details")
public class CharacterResponse {
    @ApiModelProperty(name = "ID",
            value = "ID of the character",
            dataType = "Long",
            example = "1")
    private Long id;
    @ApiModelProperty(name = "Name",
            value = "name of the character",
            dataType = "String",
            example = "Mujer Maravilla")
    private String name;
    @ApiModelProperty(name = "Age",
            value = "Age of the character",
            dataType = "int",
            example = "48")

    private Integer age;
    private Double weight;
    private String history;
    @ApiModelProperty(name = "listMovie",
            value = "listMovie of the character",
            dataType = "List<CharacterMovieResponse>")

    private List<CharacterMovieResponse> listMovie;
    /* Dto*/
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
