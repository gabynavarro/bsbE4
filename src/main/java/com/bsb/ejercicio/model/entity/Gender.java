package com.bsb.ejercicio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder @Data @AllArgsConstructor
public class Gender {
    private Long id;
    private String name;
}
