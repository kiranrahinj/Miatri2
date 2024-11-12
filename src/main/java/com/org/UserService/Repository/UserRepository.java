package com.org.UserService.Repository;

import com.org.UserService.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
    User findByEmail(String email);
}
