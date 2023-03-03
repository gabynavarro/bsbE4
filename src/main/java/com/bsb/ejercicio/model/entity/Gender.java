package com.bsb.ejercicio.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "genders")
@SQLDelete(sql = "UPDATE genders SET softDeleted = true WHERE id=?")
@Where(clause = "soft_deleted = false")
@ApiModel("Model Gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty("Gender name")
    private String name;
    @ApiModelProperty("List movieOrSeries")
    @OneToMany(targetEntity=Movie.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Movie> movieOrSeriesLis;
    private boolean softDeleted=false;
    public void addMovie(Movie m){
        movieOrSeriesLis.add(m);
    }

    public Gender(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
