package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.ShoppingCartDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not save shoppingCart", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> getShoppingCartByUser = session.createQuery(
                    "select sc from ShoppingCart sc "
                            + "left join fetch sc.tickets "
                            + "where sc.user = :user ",
                    ShoppingCart.class);
            getShoppingCartByUser.setParameter("user", user);
            return getShoppingCartByUser.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Could not get ShoppingCart by user", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not update shopping cart", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
