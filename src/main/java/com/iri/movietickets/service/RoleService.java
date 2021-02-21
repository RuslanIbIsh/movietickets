package com.iri.movietickets.service;

import com.iri.movietickets.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getRoleByName(String name);
}
