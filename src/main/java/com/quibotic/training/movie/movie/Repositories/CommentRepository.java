package com.quibotic.training.movie.movie.Repositories;

import com.quibotic.training.movie.movie.dto.Comment;
import com.quibotic.training.movie.movie.dto.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByMovieId(@Param("movieId") String movieId);
}
