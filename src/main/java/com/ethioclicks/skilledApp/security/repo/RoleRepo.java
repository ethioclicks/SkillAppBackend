package com.ethioclicks.skilledApp.security.repo;

import com.ethioclicks.skilledApp.security.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface RoleRepo extends CrudRepository<Role, Integer> {
//    Set<Role> findAllByname(String user);
    Set<Role> findAllByName(String user);
}
