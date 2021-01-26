package com.iri.movietickets;

import com.iri.movietickets.lib.Injector;
import com.iri.movietickets.model.Movie;
import com.iri.movietickets.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.iri.movietickets");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie("John Wick", "thriller");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
