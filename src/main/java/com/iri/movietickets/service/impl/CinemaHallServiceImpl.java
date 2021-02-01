package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.CinemaHallDao;
import com.iri.movietickets.lib.Inject;
import com.iri.movietickets.lib.Service;
import com.iri.movietickets.model.CinemaHall;
import com.iri.movietickets.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
