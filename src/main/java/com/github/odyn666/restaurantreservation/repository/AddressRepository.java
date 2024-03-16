package com.github.odyn666.restaurantreservation.repository;

import com.github.odyn666.restaurantreservation.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel,Long> {
}
