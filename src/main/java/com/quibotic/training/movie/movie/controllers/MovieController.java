package com.quibotic.training.movie.movie.controllers;

import com.quibotic.training.movie.movie.Repositories.MovieRepository;
import com.quibotic.training.movie.movie.dto.MovieDto;
import com.quibotic.training.movie.movie.exceptions.CommentNotFoundException;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;
import com.quibotic.training.movie.movie.models.Movie;
import com.quibotic.training.movie.movie.services.MovieService;
import com.quibotic.training.movie.movie.services.MovieServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/movie")
public class MovieController extends BaseController {

    @Autowired
    private MovieService movieService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @PostMapping
    public MovieDto saveMovie(@RequestBody MovieDto movie) throws MovieNotFoundException, CloneNotSupportedException {
        MovieDto movieDto = movieService.save(movie);

        Link link = linkTo(MovieController.class).slash(movieDto.getMovieId()).withSelfRel();
        movieDto.add(link);


        return movieDto;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header"),
    })
    @DeleteMapping("/{movieId}/OnTheater")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOnTheatersByMovieId(@PathVariable("movieId") Integer movieId) throws CommentNotFoundException {
        movieService.deleteOnTheaterByMovieId(movieId);

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header"),
    })
    @DeleteMapping("/OnTheater/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOnTheaterById(@PathVariable("id") Integer id) throws CommentNotFoundException {
        movieService.deleteOnTheaterById(id);

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header")
    })
    @GetMapping ("/{id}")
    public MovieDto getMovie(@PathVariable int id) throws MovieNotFoundException {
        MovieDto movieDto = movieService.findById(id);
        Link link = linkTo(MovieController.class).slash(movieDto.getMovieId()).withSelfRel();
        movieDto.add(link);
        return movieDto;
    }

}
