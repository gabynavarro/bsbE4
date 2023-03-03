package com.bsb.ejercicio.model.response.Gender;

import com.bsb.ejercicio.model.entity.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel(value = "GenderMovieResponse", description = "Response requested gender in movie")
public class GenderMovieResponse {
    private Long id;
    @ApiModelProperty(name = "name gender",
            value = "name of the gender",
            dataType = "String",
            example = "Terror")
    private String name;
    public static GenderMovieResponse toResponse(Gender enty){
        return GenderMovieResponse.builder()
                .id(enty.getId())
                .name(enty.getName())
                .build();
    }
}
