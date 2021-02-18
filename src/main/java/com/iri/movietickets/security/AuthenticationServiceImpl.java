package com.iri.movietickets.security;

import com.iri.movietickets.model.User;
import com.iri.movietickets.service.ShoppingCartService;
import com.iri.movietickets.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User userRegisted = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userRegisted);
        return user;
    }
}
