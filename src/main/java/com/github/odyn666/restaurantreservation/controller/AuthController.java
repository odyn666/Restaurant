package com.github.odyn666.restaurantreservation.controller;

import com.github.odyn666.restaurantreservation.dto.UserRegistrationDto;
import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.model.ReservationModel;
import com.github.odyn666.restaurantreservation.model.TableModel;
import com.github.odyn666.restaurantreservation.service.AppUserService;
import com.github.odyn666.restaurantreservation.service.ReservationService;
import com.github.odyn666.restaurantreservation.service.TableService;
import com.github.odyn666.restaurantreservation.service.exception.NotFoundException;
import com.github.odyn666.restaurantreservation.utils.AppUserUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService userService;
    private final AppUserController userController;
    private final TableService tableService;
    private final ReservationService reservationService;

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

            user = userService.findUserByEmail(dto.getEmail());
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
        userService.createUser(dto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reservation")
    public String reservation(Model model) {
        AppUserUtil util = new AppUserUtil(userService);
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("reservation", new ReservationModel());
//        model.addAttribute("user",userService.getLoggedInUser());
        return "reservation";
    }

    @GetMapping("/reservation/reserve/{id}")
    public String reserveTable(@PathVariable Long id, Model model) {
        TableModel tableByID = tableService.getTableByID(id);

        return "redirect:reservation?success";
    }
}
