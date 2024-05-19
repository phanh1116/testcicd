package com.web.studentsystem.repository;

import com.web.studentsystem.entities.Role;
import com.web.studentsystem.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
    void deleteByFirstName(String msv);
}
