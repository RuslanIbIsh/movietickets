package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.UserDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.lib.Dao;
import com.iri.movietickets.model.User;
import com.iri.movietickets.util.HibernateUtil;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not save user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> getUserByEmail = session.createQuery("select u "
                    + "from User u "
                    + "where u.email = :email ", User.class);
            getUserByEmail.setParameter("email", email);
            return getUserByEmail.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Could not find user by email" + email, e);
        }
    }
}
