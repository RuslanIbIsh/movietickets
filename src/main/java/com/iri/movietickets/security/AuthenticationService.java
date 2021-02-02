package com.iri.movietickets.security;

import com.iri.movietickets.exception.AuthenticationException;
import com.iri.movietickets.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
