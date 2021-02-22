package com.iri.movietickets.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.iri.movietickets.model.Role;
import com.iri.movietickets.model.User;
import com.iri.movietickets.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Could not fine user by email" + email));
        List<String> roles = new ArrayList<>();
        for (Role role : user.getUserRoles()) {
            roles.add(role.getRoleName());
        }
        UserBuilder userBuilder = withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(roles.toArray(new String[0]));
        return userBuilder.build();
    }
}
