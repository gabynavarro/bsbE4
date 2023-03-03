package com.bsb.ejercicio.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "CharacterRequest", description = "Format for creating and updating character")
public class CharacterRequest {
    @ApiModelProperty(name = "Name",
            value = "Name of the character",
            dataType = "String",
            notes = "cannot be null contain special characters",
            example = "Gabriel Navarro",
            required = true)
    private String name;
    @ApiModelProperty(name = "Age",
            value = "age of the character",
            dataType = "Int",
            example = "35",
            notes = "must be in the range 0-115",
            required = true)
    private Integer age;
    @ApiModelProperty(name = "Weight",
            value = "weight of the character",
            dataType = "Double",
            example = "42.6",
            required = true)
    private Double weight;
    private String history;
    @ApiModelProperty(name = "LisMovie",
            value = "list movie by id",
            dataType = "List<String>",
            example = "['1','34','3']",
            required = true)
    private List<String> listMovies;


}
