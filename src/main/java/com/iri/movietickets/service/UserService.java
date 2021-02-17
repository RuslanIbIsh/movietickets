package com.iri.movietickets.service;

import com.iri.movietickets.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    User getById(Long id);
}
