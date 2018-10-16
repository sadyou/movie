package com.quibotic.training.movie.movie.controllers;

import com.quibotic.training.movie.movie.dto.User;
import com.quibotic.training.movie.movie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public class BaseController {

    protected User user;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void getHeaders (@RequestHeader(value = "Authorization", required = true) String token){
        user = userService.validateToken(token);
    }
}
