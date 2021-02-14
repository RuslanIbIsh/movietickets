package com.iri.movietickets.service;

import com.iri.movietickets.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie get(Long id);
}
