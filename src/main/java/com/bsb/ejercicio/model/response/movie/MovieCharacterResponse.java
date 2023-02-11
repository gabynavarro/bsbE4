package com.bsb.ejercicio.model.response.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MovieCharacterResponse {
  private String name;
}
