package com.iri.movietickets.service;

import com.iri.movietickets.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    MovieSession update(Long id, MovieSession movieSession);

    void delete(Long id);
}
