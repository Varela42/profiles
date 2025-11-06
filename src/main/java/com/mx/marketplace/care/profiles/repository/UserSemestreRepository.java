package com.mx.marketplace.care.profiles.repository;

import com.mx.marketplace.care.profiles.model.entity.User;
import com.mx.marketplace.care.profiles.model.entity.UserSemestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSemestreRepository extends JpaRepository<UserSemestre, Long> {

    UserSemestre findOneByUser(User user);
}
