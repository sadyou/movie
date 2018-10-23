package com.quibotic.training.movie.movie.services;

import com.quibotic.training.movie.movie.dto.MovieDto;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;
import com.quibotic.training.movie.movie.models.Movie;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> findAll();

    List<MovieDto> findOnTheaters();

    List<MovieDto> findOnTheatersByPostCode(int postCode);

    MovieDto save(MovieDto movie) throws CloneNotSupportedException;

    boolean checkExists(int id);

    MovieDto findById(int id) throws MovieNotFoundException;

    void deleteOnTheaterByMovieId(Integer movieId);

    @Transactional
    void deleteOnTheaterById(Integer id);
}
