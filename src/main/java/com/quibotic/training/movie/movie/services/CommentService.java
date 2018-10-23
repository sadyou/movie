package com.quibotic.training.movie.movie.services;

import com.quibotic.training.movie.movie.dto.CommentDto;
import com.quibotic.training.movie.movie.dto.User;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAllByMovie(int movieId);

    CommentDto save(CommentDto comment, int movieId, User user);

    void deleteById(Integer id, User user, Integer movieId);

}
