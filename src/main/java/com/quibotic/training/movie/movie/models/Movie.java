package com.quibotic.training.movie.movie.models;

import com.quibotic.training.movie.movie.dto.MovieDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
public class Movie implements Cloneable {

    @Id
    @GenericGenerator(name="miviegen" , strategy="increment")
    @GeneratedValue(generator="miviegen")
    private Integer id;
    private String title;
    private int year;

    @OneToMany(mappedBy = "movie",fetch=FetchType.LAZY, targetEntity = OnTheater.class)
    private List<OnTheater> onTheaters;
//    List<Comment> comments;

    public static Movie getFromMovieDto(MovieDto movie) {
        if (movie == null) return null;
        return Movie.builder()
                .id(movie.getMovieId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .onTheaters(movie.getOnTheaters())
                .build();
    }

    @Override
    public Movie clone() throws CloneNotSupportedException {
        return Movie.builder().id(this.id).title(this.title).year(this.year).build();
    }
}
