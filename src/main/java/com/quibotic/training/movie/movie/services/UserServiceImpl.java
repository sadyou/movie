package com.quibotic.training.movie.movie.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quibotic.training.movie.movie.Repositories.UserRepository;
import com.quibotic.training.movie.movie.dto.LoginResponseDto;
import com.quibotic.training.movie.movie.dto.MovieDto;
import com.quibotic.training.movie.movie.dto.Token;
import com.quibotic.training.movie.movie.dto.User;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;
import com.quibotic.training.movie.movie.exceptions.TokenNotValidException;
import com.quibotic.training.movie.movie.exceptions.UserNotFoundException;
import com.quibotic.training.movie.movie.models.Movie;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findById(String username) {
        Optional<User> user = userRepository.findById(username);

        if (!user.isPresent())
            throw new UserNotFoundException("username/password not valid");

        return user.get();
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public LoginResponseDto login(String username, String password) throws JsonProcessingException {
        User user = findById(username);
        if (user.getPassword().equals(password)){
            ObjectMapper objectMapper = new ObjectMapper();
            Token token = Token.builder().expireTime((new Date()).getTime() + (60 * 15 * 1000)).user(user).build();
            String tokenString = objectMapper.writeValueAsString(token);
            tokenString = Base64.encodeBase64String(tokenString.getBytes());
            return LoginResponseDto.builder().token(tokenString).user(user).build();
        } else {
            throw new UserNotFoundException("username-" + username);
        }
    }

    @Override
    public User validateToken(String token) {
        boolean isValid = true;
        String description = "Token is not Valid";
        User user = null;
        try {
            String tokenString = new String(Base64.decodeBase64(token));
            ObjectMapper objectMapper = new ObjectMapper();
            Token tokenObject = objectMapper.readValue(tokenString, Token.class);
            if (tokenObject.getExpireTime() < System.currentTimeMillis()){
                isValid = false;
                description = "Token Expired";
            } else {
                user = findById(tokenObject.getUser().getUsername());
            }
        } catch (Exception ex){
            isValid = false;
        }

        if (isValid){
            return user;
        } else {
            throw new TokenNotValidException(description);
        }
    }
}
