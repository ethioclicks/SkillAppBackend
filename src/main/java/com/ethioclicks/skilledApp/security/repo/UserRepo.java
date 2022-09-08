package com.ethioclicks.skilledApp.security.repo;

import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUserName(String userName);

    Optional<User> findByUserPublicId(String publicId);
    List<User> findByPhoneNumber(String phone);

    Optional<User> findUserByPhoneNumber(String phoneNo);
}
