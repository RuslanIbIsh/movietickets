package com.iri.movietickets.dao;

import com.iri.movietickets.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    MovieSession update(Long id, MovieSession movieSession);

    void delete(Long id);
}
