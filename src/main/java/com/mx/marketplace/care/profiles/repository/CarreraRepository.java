package com.mx.marketplace.care.profiles.repository;

import com.mx.marketplace.care.profiles.model.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}