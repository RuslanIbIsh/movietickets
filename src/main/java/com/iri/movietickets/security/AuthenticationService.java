package com.iri.movietickets.security;

import com.iri.movietickets.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
