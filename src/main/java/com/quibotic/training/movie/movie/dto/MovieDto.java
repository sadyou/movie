package com.quibotic.training.movie.movie.dto;

import com.quibotic.training.movie.movie.models.Movie;
import com.quibotic.training.movie.movie.models.OnTheater;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto extends ResourceSupport implements Serializable {

    private int movieId;
    @NonNull
    private String title;
    private int year;
    List<OnTheater> onTheaters;
//    List<Comment> comments;

    public static MovieDto getFromMovieModel(Movie movie) {
        if (movie == null) return null;
        return MovieDto.builder()
                .movieId(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .onTheaters(movie.getOnTheaters())
                .build();
    }
}
