package com.iri.movietickets.dao;

import com.iri.movietickets.model.Role;
import java.util.Optional;

public interface RoleDao {
    Role add(Role role);

    Optional<Role> getRoleByName(String name);
}
