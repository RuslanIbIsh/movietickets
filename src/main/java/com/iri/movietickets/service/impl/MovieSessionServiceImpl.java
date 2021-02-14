package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.MovieSessionDao;
import com.iri.movietickets.model.MovieSession;
import com.iri.movietickets.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        return movieSessionDao.update(movieSession);
    }

    @Override
    public MovieSession delete(MovieSession movieSession) {
        return movieSessionDao.delete(movieSession);
    }
}
