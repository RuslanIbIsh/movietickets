package com.iri.movietickets.security;

import com.iri.movietickets.model.Role;
import com.iri.movietickets.model.User;
import com.iri.movietickets.service.RoleService;
import com.iri.movietickets.service.ShoppingCartService;
import com.iri.movietickets.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService;
    private ShoppingCartService shoppingCartService;
    private RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Role role = roleService.getRoleByName("user");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setUserRoles(roles);
        User userRegisted = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userRegisted);
        return user;
    }
}
