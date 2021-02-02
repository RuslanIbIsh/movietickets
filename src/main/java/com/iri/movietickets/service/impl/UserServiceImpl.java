package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.UserDao;
import com.iri.movietickets.lib.Inject;
import com.iri.movietickets.lib.Service;
import com.iri.movietickets.model.User;
import com.iri.movietickets.service.UserService;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
