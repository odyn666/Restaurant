package com.github.odyn666.restaurantreservation.dto;

import com.github.odyn666.restaurantreservation.model.AppUserRole;
import com.github.odyn666.restaurantreservation.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

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

    private List<UserRole> role;

    @Email
    @NotEmpty
    private String email;


}
