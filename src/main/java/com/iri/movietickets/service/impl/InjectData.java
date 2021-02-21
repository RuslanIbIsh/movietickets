package com.iri.movietickets.service.impl;

import com.iri.movietickets.model.Role;
import com.iri.movietickets.model.User;
import com.iri.movietickets.service.RoleService;
import com.iri.movietickets.service.ShoppingCartService;
import com.iri.movietickets.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InjectData {
    private RoleService roleService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    public InjectData(RoleService roleService,
                      UserService userService,
                      ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    public void init() {
        Role adminRole = new Role();
        adminRole.setRoleName("admin");
        Role userRole = new Role();
        userRole.setRoleName("user");
        roleService.add(adminRole);
        roleService.add(userRole);
        User user = new User();
        user.setEmail("john@mail.com");
        user.setPassword("1234");
        user.setUserRoles(List.of(adminRole));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
    }
}
