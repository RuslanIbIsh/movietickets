package com.iri.movietickets;

import com.iri.movietickets.exception.AuthenticationException;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.lib.Injector;
import com.iri.movietickets.model.CinemaHall;
import com.iri.movietickets.model.Movie;
import com.iri.movietickets.model.MovieSession;
import com.iri.movietickets.security.AuthenticationService;
import com.iri.movietickets.service.CinemaHallService;
import com.iri.movietickets.service.MovieService;
import com.iri.movietickets.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.iri.movietickets");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie("John Wick", "thriller");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("red");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll();

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        MovieSession movieSession = new MovieSession();
        LocalDateTime localDateTime =
                LocalDateTime.of(2021, 02, 01, 14, 00);
        movieSession.setShowTime(localDateTime);

        movieSessionService.add(movieSession);

        movieSessionService.findAvailableSessions(movieSession.getId(),
                LocalDate.of(2021, 02, 01));
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register("bob@mail.com", "1235");
        authenticationService.register("alice@mail.com", "9876");
        try {
            authenticationService.login("bob@mail.com", "1235");
        } catch (AuthenticationException e) {
            throw new DataProcessingException("Can't login user", e);
        }
    }
}
