package com.sample.users.repository;

import com.sample.users.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User findByToken(String token);

}
