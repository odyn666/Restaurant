package com.github.odyn666.restaurantreservation.dto;

import com.github.odyn666.restaurantreservation.model.AppUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String password;

    private AppUserRole role;

    @Email
    @NotEmpty
    private String email;


}
