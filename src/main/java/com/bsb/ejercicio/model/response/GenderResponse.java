package com.bsb.ejercicio.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class GenderResponse {
    private Long id;
    private String name;
}
