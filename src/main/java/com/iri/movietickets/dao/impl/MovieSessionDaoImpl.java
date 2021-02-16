package com.iri.movietickets.dao.impl;

import com.iri.movietickets.dao.MovieSessionDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> getMovieSessions = session.createQuery(
                    "select ms from MovieSession ms "
                            + "where ms.movie.id = :movieId "
                            + "and ms.showTime BETWEEN :stDate AND :edDate ",
                    MovieSession.class);
            getMovieSessions.setParameter("movieId", movieId);
            LocalDateTime stDate = date.atStartOfDay();
            getMovieSessions.setParameter("stDate", stDate);
            LocalDateTime edDate = date.atTime(LocalTime.MAX);
            getMovieSessions.setParameter("edDate", edDate);
            return getMovieSessions.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Could not get available movieSessions" + movieId
                    + ", date " + date, e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert entity" + session, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cold not update movie session"
                    + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from MovieSession m "
                    + "where m.id = :id");
            deleteQuery.setParameter("id", id);
            deleteQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not delete movie session", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
