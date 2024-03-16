package com.github.odyn666.restaurantreservation.controller;

import com.github.odyn666.restaurantreservation.dto.UserRegistrationDto;
import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.model.AppUserRole;
import com.github.odyn666.restaurantreservation.model.ReservationModel;
import com.github.odyn666.restaurantreservation.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService service;

    @GetMapping("/all")
    public ResponseEntity<List<AppUserModel>> findAllUsers() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserModel> findUserByID(@PathVariable Long id) {
        return ResponseEntity.ok(service.findUserByID(id));
    }

    @GetMapping("/find/username")
    public ResponseEntity<AppUserModel> findUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(service.findUserByUsername(username));
    }

    @GetMapping("/find/role")
    public ResponseEntity<List<AppUserModel>> findUsersByRole(@RequestParam AppUserRole role) {
        return ResponseEntity.ok(service.findUsersByRole(role));
    }

    @PostMapping("/add")
    public ResponseEntity<AppUserModel> addUserToDB(@RequestBody UserRegistrationDto dto) {
        return ResponseEntity.ok(service.createUser(dto));
    }

    @PatchMapping("/update/id")
    public ResponseEntity<AppUserModel> updateUserByID(@RequestParam Long id, @RequestBody Map<String, Object> entityFields) {
        return ResponseEntity.ok(service.updateUserByID(id, entityFields));
    }

    @PatchMapping("/update/username")
    public ResponseEntity<AppUserModel> updateUserByUsername(@RequestParam String username, @RequestBody Map<String, Object> entityFields) {
        return ResponseEntity.ok(service.updateUserByUsername(username, entityFields));
    }

    @PatchMapping("/update/body")
    public ResponseEntity<AppUserModel> updateUserByBody(@RequestBody Map<String, Object> entityFields) {
        return ResponseEntity.ok(service.updateUserFromBody(entityFields));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserByID(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }


}
