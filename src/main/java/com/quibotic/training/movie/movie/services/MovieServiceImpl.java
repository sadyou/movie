package com.quibotic.training.movie.movie.services;

import com.quibotic.training.movie.movie.Repositories.MovieRepository;
import com.quibotic.training.movie.movie.dto.MovieDto;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;
import com.quibotic.training.movie.movie.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieRepository.findAll();

        return movies.stream().map(MovieDto::getFromMovieModel).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> findOnTheaters() {
        List<Movie> movies = movieRepository.findOnTheaters();

        return movies.stream().map(MovieDto::getFromMovieModel).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> findOnTheatersByPostCode(int postCode) {
        List<Movie> movies = movieRepository.findOnTheatersByPostCode(postCode);

        return movies.stream().map(MovieDto::getFromMovieModel).collect(Collectors.toList());
    }

    @Override
    public MovieDto findById(int id) throws MovieNotFoundException {
        Optional<Movie> movie = movieRepository.findById(id);

        if (!movie.isPresent())
            throw new MovieNotFoundException("id-" + id);

        return MovieDto.getFromMovieModel(movie.get());
    }
}
