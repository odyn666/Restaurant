package com.github.odyn666.restaurantreservation.controller;

import com.github.odyn666.restaurantreservation.dto.NewRestaurantDTO;
import com.github.odyn666.restaurantreservation.model.AddressModel;
import com.github.odyn666.restaurantreservation.model.RestaurantModel;
import com.github.odyn666.restaurantreservation.model.ReviewModel;
import com.github.odyn666.restaurantreservation.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;


    @GetMapping("/find/all")
    public ResponseEntity<List<RestaurantModel>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.findAllRestaurants());
    }

    //use all methods from restaurantService similar to AppUserController
    @GetMapping("/find/{id}")
    public ResponseEntity<RestaurantModel> getRestaurantByID(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findRestaurantByID(id));
    }

    @GetMapping("/find/address")
    public ResponseEntity<RestaurantModel> getRestaurantByAddress(@RequestBody AddressModel adress) {
        return ResponseEntity.ok(restaurantService.findRestaurantByAddress(adress));
    }

    @GetMapping("/find/reviews")
    public ResponseEntity<List<ReviewModel>> getAllRestaurantReviews(Long id) {
        return ResponseEntity.ok(restaurantService.fetchRestaurantReviews(id));
    }

    @PostMapping("/add")
    public ResponseEntity<RestaurantModel> createRestaurant(@RequestBody NewRestaurantDTO restaurant) {
        return ResponseEntity.ok(restaurantService.createRestaurant(restaurant));
    }

    @PutMapping("/update")
    public ResponseEntity<RestaurantModel> updateRestaurant(@RequestBody Map<String, Object> restaurant) {
        return ResponseEntity.ok(restaurantService.updateRestaurantFromBody(restaurant));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<RestaurantModel> updateRestaurantByID(@PathVariable Long id, @RequestBody Map<String, Object> restaurant) {
        return ResponseEntity.ok(restaurantService.updateRestaurantByID(id,restaurant));
    }

    @PatchMapping("/update/name")
    public ResponseEntity<RestaurantModel> updateRestaurantByName(@RequestParam String name, @RequestBody Map<String, Object> restaurant) {
        return ResponseEntity.ok(restaurantService.updateRestaurantByName(name,restaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurantByID(id);
        return ResponseEntity.ok().build();
    }


}
