package com.quibotic.training.movie.movie.services;

import com.quibotic.training.movie.movie.Repositories.MovieRepository;
import com.quibotic.training.movie.movie.Repositories.OnTheaterRepository;
import com.quibotic.training.movie.movie.dto.MovieDto;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;
import com.quibotic.training.movie.movie.models.Movie;
import com.quibotic.training.movie.movie.models.OnTheater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private OnTheaterRepository onTheaterRepository;

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
    @Transactional
    public MovieDto save(MovieDto movie) throws CloneNotSupportedException {

        Movie movieModel = Movie.getFromMovieDto(movie);
        Movie savedModel = movieRepository.save(movieModel);
        if (movieModel.getOnTheaters() != null && movieModel.getOnTheaters().size() > 0) {
            onTheaterRepository.deleteOnTheatersByMovieId(movieModel.getId());
            for (OnTheater onTheater : movieModel.getOnTheaters()) {
                onTheater.setMovie(savedModel);
                onTheater.setId(null);
                onTheaterRepository.save(onTheater);
            }
        }

        savedModel.setOnTheaters(movieModel.getOnTheaters());

        return MovieDto.getFromMovieModel(movieModel);
    }

    @Override
    public boolean checkExists(int id) {
        return movieRepository.existsById(id);
    }

    @Override
    public MovieDto findById(int id) throws MovieNotFoundException {
        Optional<Movie> movie = movieRepository.findById(id);

        if (!movie.isPresent())
            throw new MovieNotFoundException("id-" + id);

        return MovieDto.getFromMovieModel(movie.get());
    }


    @Override
    @Transactional
    public void deleteOnTheaterByMovieId(Integer movieId){
        onTheaterRepository.deleteOnTheatersByMovieId(movieId);
    }

    @Override
    @Transactional
    public void deleteOnTheaterById(Integer id){
        onTheaterRepository.deleteOnTheatersById(id);
    }
}
