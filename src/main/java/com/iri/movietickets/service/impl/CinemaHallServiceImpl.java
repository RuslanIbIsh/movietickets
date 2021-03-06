package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.CinemaHallDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.CinemaHall;
import com.iri.movietickets.service.CinemaHallService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private CinemaHallDao cinemaHallDao;

    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }

    @Override
    public CinemaHall get(Long id) {
        return cinemaHallDao.get(id).orElseThrow(() ->
                new DataProcessingException("CinemaHall service could not get "
                        + "cinema hall by id"));
    }
}
