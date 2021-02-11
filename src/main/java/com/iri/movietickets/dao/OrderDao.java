package com.iri.movietickets.dao;

import com.iri.movietickets.model.Order;
import com.iri.movietickets.model.User;
import java.util.List;

public interface OrderDao {
    List<Order> getOrderHistory(User user);

    Order add(Order order);
}
