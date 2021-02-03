package com.iri.movietickets.service;

import com.iri.movietickets.model.MovieSession;
import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
