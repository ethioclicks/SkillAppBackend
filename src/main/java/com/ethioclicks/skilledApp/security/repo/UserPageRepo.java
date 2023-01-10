package com.ethioclicks.skilledApp.security.repo;


import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPageRepo extends PagingAndSortingRepository<User, Long> {
}