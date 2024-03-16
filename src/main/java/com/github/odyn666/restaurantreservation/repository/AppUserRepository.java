package com.github.odyn666.restaurantreservation.repository;

import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.model.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserModel, Long> {

    Optional<AppUserModel> findAppUserModelById(Long id);

    Optional<List<AppUserModel>> findAppUserModelByAppUserRole(AppUserRole role);

    Optional<AppUserModel> findAppUserModelByUsername(String username);

    Boolean deleteAppUserModelById(Long id);

    Optional<AppUserModel> findAppUserModelByEmail(String email);

}
