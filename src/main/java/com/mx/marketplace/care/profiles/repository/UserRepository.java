package com.mx.marketplace.care.profiles.repository;

import com.mx.marketplace.care.profiles.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);
}
