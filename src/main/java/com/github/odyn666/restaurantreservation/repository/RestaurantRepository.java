package com.github.odyn666.restaurantreservation.repository;

import com.github.odyn666.restaurantreservation.model.AddressModel;
import com.github.odyn666.restaurantreservation.model.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {
    Optional<RestaurantModel> findRestaurantModelById(Long id);

    Optional<RestaurantModel> findRestaurantModelByAddress(AddressModel adress);

    Optional<RestaurantModel> findRestaurantModelByName(String username);
}
