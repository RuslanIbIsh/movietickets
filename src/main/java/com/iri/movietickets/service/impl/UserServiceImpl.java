package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.UserDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.User;
import com.iri.movietickets.service.UserService;
import com.iri.movietickets.util.HashUtil;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        String hashedPassword = HashUtil.hashPassword(user.getPassword(), user.getSalt());
        user.setPassword(hashedPassword);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id).orElseThrow(() ->
                new DataProcessingException("Could not get user"));
    }
}
