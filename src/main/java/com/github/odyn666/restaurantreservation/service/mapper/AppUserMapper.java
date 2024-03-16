package com.github.odyn666.restaurantreservation.service.mapper;

import com.github.odyn666.restaurantreservation.dto.UserRegistrationDto;
import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.model.AppUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class AppUserMapper {



    public static AppUserModel toEntity(UserRegistrationDto dto) {
        AppUserModel entity = new AppUserModel();


        return AppUserModel.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .appUserRole(AppUserRole.USER)
                .creationDate(LocalDateTime.now())
                .build();
    }


    public static UserRegistrationDto toDTO(AppUserModel model) {
        return UserRegistrationDto.builder()
                .firstname(model.getFirstname())
                .lastname(model.getLastname())
                .password(model.getPassword())
                .email(model.getEmail()).build();
    }
}
