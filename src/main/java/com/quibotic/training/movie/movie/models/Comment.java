package com.quibotic.training.movie.movie.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quibotic.training.movie.movie.models.Movie;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @CreationTimestamp()
    private Date createdDate;
    @UpdateTimestamp
    private Date updatedDate;
    private String text;
    private String author;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Movie movie;
}
