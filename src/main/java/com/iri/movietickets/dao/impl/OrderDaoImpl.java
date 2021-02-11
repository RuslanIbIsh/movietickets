package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.OrderDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.lib.Dao;
import com.iri.movietickets.model.Order;
import com.iri.movietickets.model.User;
import com.iri.movietickets.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
            session = HibernateUtil.getSessionFactory().openSession();
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
