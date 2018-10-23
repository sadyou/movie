package com.quibotic.training.movie.movie.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quibotic.training.movie.movie.models.Movie;
import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OnTheaterDto {

    private Integer id;
    private int postCode;
    private String state;
    private String theaterName;
    private MovieDto movie;

}
