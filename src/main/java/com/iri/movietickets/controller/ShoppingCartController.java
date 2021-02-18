package com.iri.movietickets.controller;

import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.MovieSession;
import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.User;
import com.iri.movietickets.model.dto.ShoppingCartResponseDto;
import com.iri.movietickets.service.MovieSessionService;
import com.iri.movietickets.service.ShoppingCartService;
import com.iri.movietickets.service.UserService;
import com.iri.movietickets.service.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private UserService userService;
    private ShoppingCartService shoppingCartService;
    private MovieSessionService movieSessionService;
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartController(UserService userService,
                                  ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authentication,
                                @RequestParam Long movieSessionId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() ->
                new DataProcessingException("Could not find user by email"));
        MovieSession movieSession = movieSessionService.getById(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() ->
                new DataProcessingException("Could not find user by email"));
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return shoppingCartMapper.convertFromShoppingCart(shoppingCart);
    }
}
