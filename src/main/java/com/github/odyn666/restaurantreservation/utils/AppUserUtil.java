package com.github.odyn666.restaurantreservation.utils;

import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;



public class AppUserUtil {

    private final AppUserService userService;

    public AppUserUtil(AppUserService userService) {
        this.userService = userService;
    }

    public String createUsername(String firstname, String lastName) {

        return firstname.charAt(0) + "." + lastName;


    }

    private static String changeExistingUsername(String username) {
        char ch = username.charAt(username.length() - 1);
        if (Character.isDigit(ch)) {
            return username + (Integer.parseInt(String.valueOf(ch))) + 1;
        }
        return username + 1;
    }


}
