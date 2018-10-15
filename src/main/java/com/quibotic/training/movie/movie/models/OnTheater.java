package com.quibotic.training.movie.movie.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OnTheater {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int postCode;
    private String state;
    private String theaterName;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Movie movie;

}
