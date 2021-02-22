package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.RoleDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could save role" + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> getRoleByName = session.createQuery("select r "
                    + "from Role r "
                    + "where r.roleName = :name ", Role.class);
            getRoleByName.setParameter("name", name);
            return getRoleByName.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Could not find role by roleName"
                    + name, e);
        }
    }
}
