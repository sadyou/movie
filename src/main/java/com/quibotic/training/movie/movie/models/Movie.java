package com.quibotic.training.movie.movie.models;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private int year;

    @OneToMany(mappedBy = "movie")
    private List<OnTheater> onTheaters;
//    List<Comment> comments;
}
