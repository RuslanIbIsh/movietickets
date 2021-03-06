package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.ShoppingCartDao;
import com.iri.movietickets.dao.TicketDao;
import com.iri.movietickets.model.MovieSession;
import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.Ticket;
import com.iri.movietickets.model.User;
import com.iri.movietickets.service.ShoppingCartService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartDao shoppingCartDao;
    private TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        if (shoppingCart != null) {
            shoppingCart.getTickets().add(ticketDao.add(ticket));
            shoppingCartDao.update(shoppingCart);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.update(shoppingCart);
    }
}
