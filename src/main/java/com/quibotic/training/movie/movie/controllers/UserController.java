package com.quibotic.training.movie.movie.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quibotic.training.movie.movie.dto.LoginDto;
import com.quibotic.training.movie.movie.dto.LoginResponseDto;
import com.quibotic.training.movie.movie.dto.MovieDto;
import com.quibotic.training.movie.movie.dto.User;
import com.quibotic.training.movie.movie.exceptions.MovieNotFoundException;
import com.quibotic.training.movie.movie.exceptions.UserNotFoundException;
import com.quibotic.training.movie.movie.services.MovieService;
import com.quibotic.training.movie.movie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User saveUser(@Valid @RequestBody User user)  {
        return userService.save(user);
    }

    @PostMapping ("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginDto loginDto) throws UserNotFoundException, JsonProcessingException {
        LoginResponseDto token = userService.login(loginDto.getUsername(), loginDto.getPassword());
        return token;
    }

}
