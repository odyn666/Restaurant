package com.github.odyn666.restaurantreservation.repository;

import com.github.odyn666.restaurantreservation.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationModel,Long> {
}
