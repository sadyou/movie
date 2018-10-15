package com.quibotic.training.movie.movie.controllers;

import com.quibotic.training.movie.movie.Repositories.MovieRepository;
import com.quibotic.training.movie.movie.dto.MovieDto;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;
import com.quibotic.training.movie.movie.services.MovieService;
import com.quibotic.training.movie.movie.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping
    public Resources<MovieDto> getMovies() throws MovieNotFoundException {
        List<MovieDto> movieList = movieService.findAll();

        for (final MovieDto movie : movieList) {
            final Link selfLink = linkTo(methodOn(MovieController.class).getMovie(movie.getMovieId())).withSelfRel();
            movie.add(selfLink);
        }

        Link link =linkTo(methodOn(MovieController.class).getMovies()).withSelfRel();
        Resources<MovieDto> result = new Resources<MovieDto>(movieList, link);

        return result;
    }

    @GetMapping("/OnTheater")
    public Resources<MovieDto> getMoviesOnTheater() throws MovieNotFoundException {
        List<MovieDto> movieList = movieService.findOnTheaters();

        for (final MovieDto movie : movieList) {
            final Link selfLink = linkTo(methodOn(MovieController.class).getMovie(movie.getMovieId())).withSelfRel();
            movie.add(selfLink);
        }

        Link link =linkTo(methodOn(MovieController.class).getMovies()).withSelfRel();
        Resources<MovieDto> result = new Resources<MovieDto>(movieList, link);

        return result;
    }

    @GetMapping("/OnTheater/{postCode}")
    public Resources<MovieDto> getMoviesOnTheaterByPostCode(@PathVariable int postCode) throws MovieNotFoundException {
        List<MovieDto> movieList = movieService.findOnTheatersByPostCode(postCode);

        for (final MovieDto movie : movieList) {
            final Link selfLink = linkTo(methodOn(MovieController.class).getMovie(movie.getMovieId())).withSelfRel();
            movie.add(selfLink);
        }

        Link link =linkTo(methodOn(MovieController.class).getMovies()).withSelfRel();
        Resources<MovieDto> result = new Resources<MovieDto>(movieList, link);

        return result;
    }

    @GetMapping ("/{id}")
    public MovieDto getMovie(@PathVariable int id) throws MovieNotFoundException {
        MovieDto movieDto = movieService.findById(id);
        Link link = linkTo(MovieController.class).slash(movieDto.getMovieId()).withSelfRel();
        movieDto.add(link);
        return movieDto;
    }

}
