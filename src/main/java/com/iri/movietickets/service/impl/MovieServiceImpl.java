package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.MovieDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.Movie;
import com.iri.movietickets.service.MovieService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(() ->
                new DataProcessingException("Movie session service could not get"
                        + "movie by id"));
    }
}
