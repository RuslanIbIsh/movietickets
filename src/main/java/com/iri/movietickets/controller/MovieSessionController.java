package com.iri.movietickets.controller;

import com.iri.movietickets.model.dto.MovieSessionRequestDto;
import com.iri.movietickets.model.dto.MovieSessionResponseDto;
import com.iri.movietickets.service.MovieSessionService;
import com.iri.movietickets.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class MovieSessionController {
    private final MovieSessionMapper movieSessionMapper;
    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionMapper movieSessionMapper,
                                  MovieSessionService movieSessionService) {
        this.movieSessionMapper = movieSessionMapper;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailable(@RequestParam Long movieId,
                                                      @RequestParam @DateTimeFormat
                                                              (pattern = "dd.MM.yyyy")
                                                              LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.convertFromDto(movieSessionRequestDto));
    }

    @PutMapping
    public void update(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.update(movieSessionMapper.convertFromDto(movieSessionRequestDto));
    }

    @DeleteMapping
    public void delete(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.delete(movieSessionMapper.convertFromDto(movieSessionRequestDto));
    }
}
