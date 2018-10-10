package com.quibotic.training.movie.movie.Repositories;

import com.quibotic.training.movie.movie.dto.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "movie", path = "movie")
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitle(@Param("name") String title);
}
