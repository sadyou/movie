package com.quibotic.training.movie.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quibotic.training.movie.movie.models.Comment;
import com.quibotic.training.movie.movie.models.Movie;
import com.quibotic.training.movie.movie.validators.ViewCommentGroup;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto extends ResourceSupport implements Serializable {

    private Integer commentId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedDate;
    @NotNull
    private String text;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String author;

    public static CommentDto fromCommentModel (Comment comment){
        return CommentDto.builder()
                .author(comment.getAuthor())
                .createdDate(comment.getCreatedDate())
                .commentId(comment.getId())
                .text(comment.getText())
                .updatedDate(comment.getUpdatedDate())
                .build();
    }


}
