package com.bsb.ejercicio.model.entity;

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
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(targetEntity=Movie.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Movie> movieOrSeriesLis;
    private boolean softDeleted=false;
    public void addMovie(Movie m){
        movieOrSeriesLis.add(m);
    }
}
