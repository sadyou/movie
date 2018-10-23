package com.quibotic.training.movie.movie.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Embeddable
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OnTheater {

    @Id
    @GenericGenerator(name="theatergen" , strategy="increment")
    @GeneratedValue(generator="theatergen")
    @JsonIgnore
    private Integer id;
    private int postCode;
    private String state;
    private String theaterName;
   @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", insertable = true, updatable = true, nullable = true)
 /*    @OnDelete(action = OnDeleteAction.CASCADE)*/
    @JsonIgnore
    private Movie movie;

}
