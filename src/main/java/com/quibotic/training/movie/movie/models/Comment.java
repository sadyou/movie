package com.quibotic.training.movie.movie.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quibotic.training.movie.movie.dto.CommentDto;
import com.quibotic.training.movie.movie.models.Movie;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.persistence.Entity;
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
    @GenericGenerator(name="commentgen" , strategy="increment")
    @GeneratedValue(generator="commentgen")
    private Integer id;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;
    @UpdateTimestamp
    private Date updatedDate;
    @Lob
    private String text;
    private String author;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Movie movie;

    public static Comment fromCommentDto (CommentDto comment){
        return Comment.builder()
                .author(comment.getAuthor())
                .id(comment.getCommentId())
                .text(comment.getText())
                .build();
    }
}
