package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.UserDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
        try (Session session = sessionFactory.openSession()) {
            Query<User> getUserByEmail = session.createQuery("select u "
                    + "from User u "
                    + "join fetch u.userRoles "
                    + "where u.email = :email ", User.class);
            getUserByEmail.setParameter("email", email);
            return getUserByEmail.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Could not find user by email" + email, e);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> getById = session.createQuery("select u "
                    + "from User u "
                    + "join fetch u.userRoles where u.id = :id ", User.class);
            getById.setParameter("id", id);
            return getById.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Could not get user by id", e);
        }
    }
}
