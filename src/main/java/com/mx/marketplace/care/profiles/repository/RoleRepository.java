package com.mx.marketplace.care.profiles.repository;

import com.mx.marketplace.care.profiles.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
