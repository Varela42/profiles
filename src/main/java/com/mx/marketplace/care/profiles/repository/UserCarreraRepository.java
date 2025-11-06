package com.mx.marketplace.care.profiles.repository;

import com.mx.marketplace.care.profiles.model.entity.Carrera;
import com.mx.marketplace.care.profiles.model.entity.User;
import com.mx.marketplace.care.profiles.model.entity.UserCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCarreraRepository extends JpaRepository<UserCarrera, Long> {

    List<UserCarrera> findAllByUser(User user);
}
