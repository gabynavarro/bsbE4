package com.bsb.ejercicio.model.entity;

import lombok.Builder;

@Builder
public class Gender {
    private Long id;
    private String name;

    public Gender(String name) {
        this.name = name;
    }

    public Gender(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
