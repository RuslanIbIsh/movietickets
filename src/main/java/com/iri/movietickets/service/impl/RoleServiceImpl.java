package com.iri.movietickets.service.impl;

import com.iri.movietickets.dao.RoleDao;
import com.iri.movietickets.exception.DataProcessingException;
import com.iri.movietickets.model.Role;
import com.iri.movietickets.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name).orElseThrow(() ->
                new DataProcessingException("Could not get Role"));
    }
}
