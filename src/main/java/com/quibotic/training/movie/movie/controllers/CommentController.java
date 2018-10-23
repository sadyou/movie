package com.quibotic.training.movie.movie.controllers;

import com.quibotic.training.movie.movie.dto.CommentDto;
import com.quibotic.training.movie.movie.exceptions.CommentNotFoundException;
import com.quibotic.training.movie.movie.services.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/movie/{movieId}/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    private int movieId;

    @ModelAttribute
    public void getMovieId (@PathVariable(value = "movieId", required = true) int movieId){
        this.movieId = movieId;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header"),
            @ApiImplicitParam(name = "movieId", required = true, paramType = "path")
    })
    @GetMapping
    public List<CommentDto> getCommentsByMovie() throws CommentNotFoundException {
        List<CommentDto> commentDtos = commentService.findAllByMovie(movieId);

        return commentDtos;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header"),
            @ApiImplicitParam(name = "movieId", required = true, paramType = "path")
    })
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CommentDto saveComment(@RequestBody CommentDto comment) throws CommentNotFoundException {
        CommentDto commentDto = commentService.save(comment, movieId, user);

        return commentDto;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, paramType = "header"),
            @ApiImplicitParam(name = "movieId", required = true, paramType = "path")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") Integer id) throws CommentNotFoundException {
        commentService.deleteById(id, user, movieId);

    }

}
