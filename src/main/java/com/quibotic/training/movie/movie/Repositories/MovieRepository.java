package com.quibotic.training.movie.movie.Repositories;

import com.quibotic.training.movie.movie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitle(@Param("name") String title);
    @Query("SELECT distinct m FROM Movie m, OnTheater o WHERE m.id = o.movie.id")
    List<Movie> findOnTheaters();
    @Query("SELECT distinct m FROM Movie m, OnTheater o WHERE m.id = o.movie.id and o.postCode = :postCode")
    List<Movie> findOnTheatersByPostCode(@Param("postCode") int postCode);
}
