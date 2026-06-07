package com.wheelson66.userservice.Repository;

import com.wheelson66.userservice.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
