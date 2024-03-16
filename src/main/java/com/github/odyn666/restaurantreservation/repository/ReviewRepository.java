package com.github.odyn666.restaurantreservation.repository;

import com.github.odyn666.restaurantreservation.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewModel,Long> {
}
