package com.iri.movietickets.controller;

import com.iri.movietickets.model.dto.MovieRequestDto;
import com.iri.movietickets.model.dto.MovieResponseDto;
import com.iri.movietickets.service.MovieService;
import com.iri.movietickets.service.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieMapper movieMapper;
    private final MovieService movieService;

    public MovieController(MovieMapper movieMapper, MovieService movieService) {
        this.movieMapper = movieMapper;
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(movieMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.convertFromDto(movieRequestDto));
    }
}
