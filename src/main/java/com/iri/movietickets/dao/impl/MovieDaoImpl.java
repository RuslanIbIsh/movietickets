package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.MovieDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.lib.Dao;
import com.iri.movietickets.model.Movie;
import com.iri.movietickets.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert entity" + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getAllMovies = session.createQuery("from Movie", Movie.class);
            return getAllMovies.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Could not execute query", e);
        }
    }
}
