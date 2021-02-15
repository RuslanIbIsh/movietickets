package com.iri.movietickets.service.mapper;

import com.iri.movietickets.model.MovieSession;
import com.iri.movietickets.model.dto.MovieSessionRequestDto;
import com.iri.movietickets.model.dto.MovieSessionResponseDto;
import com.iri.movietickets.service.CinemaHallService;
import com.iri.movietickets.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto convertToDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto =
                new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        return movieSessionResponseDto;
    }

    public MovieSession convertFromDto(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto.getCinemaHallId()));
        return movieSession;
    }
}
