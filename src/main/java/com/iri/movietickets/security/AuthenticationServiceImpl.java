package com.iri.movietickets.security;

import com.iri.movietickets.exception.AuthenticationException;
import com.iri.movietickets.lib.Inject;
import com.iri.movietickets.lib.Service;
import com.iri.movietickets.model.User;
import com.iri.movietickets.service.ShoppingCartService;
import com.iri.movietickets.service.UserService;
import com.iri.movietickets.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            String hashedPassword = HashUtil.hashPassword(password, user.get().getSalt());
            if (user.get().getPassword().equals(hashedPassword)) {
                return user.get();
            }
        }
        throw new AuthenticationException("Wrong email or password");
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
