package com.quibotic.training.movie.movie.Repositories;

import com.quibotic.training.movie.movie.models.Movie;
import com.quibotic.training.movie.movie.models.OnTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnTheaterRepository extends JpaRepository<OnTheater, Integer> {

    @Modifying
    @Query("delete FROM OnTheater o WHERE o.movie.id = :movieId")
    void deleteOnTheatersByMovieId(@Param("movieId") Integer movieId);

    @Modifying
    @Query("delete FROM OnTheater o WHERE o.id = :id")
    void deleteOnTheatersById(@Param("id") Integer id);
}
