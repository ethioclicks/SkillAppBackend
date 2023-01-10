package com.ethioclicks.skilledApp.security.repo;

import com.ethioclicks.skilledApp.security.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    Set<Role> findAllByName(String user);
}
