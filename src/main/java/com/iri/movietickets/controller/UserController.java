package com.iri.movietickets.controller;

import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.User;
import com.iri.movietickets.model.dto.UserResponseDto;
import com.iri.movietickets.service.UserService;
import com.iri.movietickets.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(() ->
                new DataProcessingException("User service could not get user"));
        return userMapper.convertFromUser(user);
    }
}
