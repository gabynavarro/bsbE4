package com.bsb.ejercicio.model.response.movie;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@ApiModel(value = "MovieCharacterResponse", description = "response of movie in a list characters")
public class MovieCharacterResponse {
  @ApiModelProperty(name = "Name",
          value = "name of the character",
          dataType = "String",
          example = "Mujer Maravilla")

  private String name;
}
