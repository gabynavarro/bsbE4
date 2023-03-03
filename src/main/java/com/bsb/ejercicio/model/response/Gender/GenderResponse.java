package com.bsb.ejercicio.model.response.Gender;


import com.bsb.ejercicio.model.response.movie.MovieGenderResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
@ApiModel(value = "Gender Response", description = "Response with the requested gender details")
public class GenderResponse {
    @ApiModelProperty(name = "ID",
            value = "ID of the character",
            dataType = "Long",
            example = "1")
    private Long id;
    @ApiModelProperty(name = "name",
            value = "name  of the gender",
            dataType = "String",
            example = "Terror")
    private String name;
    private List<MovieGenderResponse> movieOrSeriesLis;
}
