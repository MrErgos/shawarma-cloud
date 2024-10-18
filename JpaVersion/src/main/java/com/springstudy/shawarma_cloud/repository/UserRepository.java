package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
