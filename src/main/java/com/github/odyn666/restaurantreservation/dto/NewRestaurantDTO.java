package com.github.odyn666.restaurantreservation.dto;

import com.github.odyn666.restaurantreservation.model.AddressModel;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewRestaurantDTO {
    private String name;
    private AddressModel address;
    private LocalTime openTime;
    private LocalTime closeTime;

}
