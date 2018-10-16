package com.quibotic.training.movie.movie.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quibotic.training.movie.movie.dto.LoginResponseDto;
import com.quibotic.training.movie.movie.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById (String userId);
    User save (User user);

    LoginResponseDto login(String username, String password) throws JsonProcessingException;

    User validateToken(String token);
}
