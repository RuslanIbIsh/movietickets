package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.MovieDao;
import com.iri.movietickets.lib.Inject;
import com.iri.movietickets.lib.Service;
import com.iri.movietickets.model.Movie;
import com.iri.movietickets.service.MovieService;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
