package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.OrderDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.Order;
import com.iri.movietickets.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> getUserOrders = session.createQuery("select distinct o from Order o "
                            + "left join fetch o.tickets "
                            + "where o.user = :user ",
                    Order.class);
            getUserOrders.setParameter("user", user);
            return getUserOrders.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Could not get user orders", e);
        }
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not add order ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
