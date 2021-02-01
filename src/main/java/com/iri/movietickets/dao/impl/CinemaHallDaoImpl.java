package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.CinemaHallDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.lib.Dao;
import com.iri.movietickets.model.CinemaHall;
import com.iri.movietickets.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert entity" + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<CinemaHall> getAllCinemaHalls =
                    session.createQuery("from CinemaHall ", CinemaHall.class);
            return getAllCinemaHalls.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Could not execute quey", e);
        }
    }
}
