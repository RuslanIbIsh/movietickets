package com.iri.movietickets.service;

import com.iri.movietickets.model.Order;
import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrderHistory(User user);
}
