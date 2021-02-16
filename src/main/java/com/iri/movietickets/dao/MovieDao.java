package com.iri.movietickets.dao;

import com.iri.movietickets.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();

    Optional<Movie> get(Long id);
}
