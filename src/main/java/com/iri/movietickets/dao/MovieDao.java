package com.iri.movietickets.dao;

import com.iri.movietickets.model.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
