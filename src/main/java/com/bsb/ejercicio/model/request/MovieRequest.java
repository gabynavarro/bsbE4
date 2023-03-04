package com.bsb.ejercicio.model.request;

import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Builder
@ApiModel(value = "CharacterRequest", description = "Format for creating and updating character")
public class MovieRequest {
    @ApiModelProperty(name = "Title",
                     value = "title of the movie",
                     dataType = "String",
                     notes = "cannot be null contain special characters",
                     example = "Mamá",
                     required = true)
    private String title;
    @ApiModelProperty(name = "Date",
            value = "date of the movie",
            dataType = "LocalDate",
            notes = "Release date movie",
            example = "2001-03-03",
            required = true)
    private LocalDate date;
    @ApiModelProperty(name = "Score",
            value = "Score of the movie",
            dataType = "int",
            notes = "Movie rating",
            example = "3",
            required = true)
    private int score;
    @ApiModelProperty(name = "idCharacter",
            value = "list id characters",
            dataType = "String",
            notes = "character id lists ",
            example = "['1','3','15']",
            required = true)
    private List<String> idCharacters;
    @ApiModelProperty(name = "gender",
            value = "id Gender",
            dataType = "Long",
            notes = "id the gender ",
            example = "3",
            required = true)
    private long gender;
}
