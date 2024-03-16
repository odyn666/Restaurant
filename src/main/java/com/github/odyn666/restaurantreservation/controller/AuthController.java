package com.github.odyn666.restaurantreservation.controller;

import com.github.odyn666.restaurantreservation.dto.UserRegistrationDto;
import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.service.AppUserService;
import com.github.odyn666.restaurantreservation.service.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService service;
    private final AppUserController userController;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);


        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserRegistrationDto dto,
                               BindingResult result, Model model) {
        AppUserModel user;
        try {

        user = service.findUserByEmail(dto.getEmail());
        } catch (NotFoundException e) {
            user = null;
        }

        if (user != null) {
            result.rejectValue("email", "error.email", "Email already exists");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", dto);
            return "/register";
        }
        service.createUser(dto);
        return "redirect::/register?success";
    }
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", service.findAllUsers());

        return "users";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
