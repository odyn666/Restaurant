package com.github.odyn666.restaurantreservation.service.mapper;

import com.github.odyn666.restaurantreservation.dto.NewRestaurantDTO;
import com.github.odyn666.restaurantreservation.model.RestaurantModel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RestaurantMapper {

    public  NewRestaurantDTO toDto(RestaurantModel model) {
        return NewRestaurantDTO.builder()
                .address(model.getAddress())
                .openTime(LocalTime.parse(model.getOpenTime().format(DateTimeFormatter.ofPattern("HH:mm"))))
                .closeTime(LocalTime.parse(model.getCloseTime().format(DateTimeFormatter.ofPattern("HH:mm"))))
                .name(model.getName())
                .build();
    }

    public  RestaurantModel toModel(NewRestaurantDTO dto) {
        return RestaurantModel.builder()
                .address(dto.getAddress())
                .openTime(LocalTime.parse(dto.getOpenTime().format(DateTimeFormatter.ofPattern("HH:mm"))))
                .closeTime(LocalTime.parse(dto.getCloseTime().format(DateTimeFormatter.ofPattern("HH:mm"))))
                .name(dto.getName())
                .build();
    }
}
