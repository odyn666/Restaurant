package com.github.odyn666.restaurantreservation.repository;

import com.github.odyn666.restaurantreservation.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole,Long> {

    UserRole findByName(String name);
}
