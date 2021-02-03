package com.iri.movietickets.dao;

import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
