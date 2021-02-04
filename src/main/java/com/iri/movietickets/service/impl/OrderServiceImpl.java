package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.OrderDao;
import com.iri.movietickets.lib.Inject;
import com.iri.movietickets.lib.Service;
import com.iri.movietickets.model.Order;
import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.User;
import com.iri.movietickets.service.OrderService;
import com.iri.movietickets.service.ShoppingCartService;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setTickets(new ArrayList<>(shoppingCart.getTickets()));
        orderDao.add(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
