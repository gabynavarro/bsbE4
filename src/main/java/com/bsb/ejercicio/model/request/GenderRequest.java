package com.bsb.ejercicio.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel(value = "Gender Request", description = "Format for creating and updating gender")
public class GenderRequest {
    @ApiModelProperty(value = "Name gender Request",
            dataType = "String",
            notes = "cannot be null contain special characters",
            example = "Terror",
            required = true)
    private String name;
}
