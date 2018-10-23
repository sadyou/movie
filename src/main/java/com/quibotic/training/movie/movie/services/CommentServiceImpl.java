package com.quibotic.training.movie.movie.services;

import com.quibotic.training.movie.movie.Repositories.CommentRepository;
import com.quibotic.training.movie.movie.dto.CommentDto;
import com.quibotic.training.movie.movie.dto.User;
import com.quibotic.training.movie.movie.exceptions.AccessDeniedException;
import com.quibotic.training.movie.movie.exceptions.CommentNotFoundException;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;
import com.quibotic.training.movie.movie.models.Comment;
import com.quibotic.training.movie.movie.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
@Autowired
    private MovieService movieService;

    @Override
    public List<CommentDto> findAllByMovie(int movieId) {
        List<Comment> comments = commentRepository.findByMovieId(movieId);
        return comments.stream().map(CommentDto::fromCommentModel).collect(Collectors.toList());
    }

    @Override
    public CommentDto save(CommentDto comment, int movieId, User user) {
        try {
            if (comment.getId() != null && (user == null || !user.getUsername().equalsIgnoreCase(comment.getAuthor()))) {
                throw new AccessDeniedException(user.getUsername() + " does not have access to edit this comment");
            }
            if (!movieService.checkExists(movieId)) {
                throw new MovieNotFoundException("Movie not found");
            }

            Comment commentModel = Comment.fromCommentDto(comment);
            commentModel.setMovie(Movie.builder().id(movieId).build());
            if (comment.getId() == null) {
                commentModel.setAuthor(user.getUsername());
            }
            return CommentDto.fromCommentModel(commentRepository.save(commentModel));
        } catch (DataIntegrityViolationException ex){
            throw new CommentNotFoundException("Comment not found");
        }
    }

    @Override
    public void deleteById(Integer id, User user, Integer movieId) {

        try {
            if (id == null) {
                throw new CommentNotFoundException("Comment Does not exist");
            }

            if (!movieService.checkExists(movieId)) {
                throw new MovieNotFoundException("Movie not found");
            }

            Optional<Comment> comment = commentRepository.findById(id);

            if (comment.isPresent() && (user == null || !user.getUsername().equalsIgnoreCase(comment.get().getAuthor()))) {
                throw new AccessDeniedException(user.getUsername() + " does not have access to edit this comment");
            }
            if (!commentRepository.existsById(id)) {
                throw new CommentNotFoundException("Comment not found");
            }

            commentRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex){
            throw new CommentNotFoundException("Comment not found");
        }

    }
}
