package com.mx.marketplace.care.profiles.repository;

import com.mx.marketplace.care.profiles.model.entity.User;
import com.mx.marketplace.care.profiles.model.entity.UserMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMateriasRepository extends JpaRepository<UserMateria, Long> {

    List<UserMateria> findAllByUser(User user);
}
