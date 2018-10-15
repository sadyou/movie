package com.quibotic.training.movie.movie.services;

import com.quibotic.training.movie.movie.dto.MovieDto;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> findAll();

    List<MovieDto> findOnTheaters();

    List<MovieDto> findOnTheatersByPostCode(int postCode);

    MovieDto findById(int id) throws MovieNotFoundException;
}
