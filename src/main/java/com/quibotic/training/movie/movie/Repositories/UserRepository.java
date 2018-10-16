package com.quibotic.training.movie.movie.Repositories;

import com.quibotic.training.movie.movie.dto.User;
import com.quibotic.training.movie.movie.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<Comment> findByUsername(@Param("username") String username);
}
