package com.github.odyn666.restaurantreservation.service;

import com.github.odyn666.restaurantreservation.dto.NewRestaurantDTO;
import com.github.odyn666.restaurantreservation.model.AddressModel;
import com.github.odyn666.restaurantreservation.model.RestaurantModel;
import com.github.odyn666.restaurantreservation.model.ReviewModel;
import com.github.odyn666.restaurantreservation.repository.RestaurantRepository;
import com.github.odyn666.restaurantreservation.service.exception.NotFoundException;
import com.github.odyn666.restaurantreservation.service.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;
    @Transactional

    public RestaurantModel createRestaurant(NewRestaurantDTO dto) {
        //TODO CHECK IF USER EXISTS
        RestaurantMapper mapper = new RestaurantMapper();
        RestaurantModel model = mapper.toModel(dto);


        return restaurantRepo.save(model);
    }

    //*READ
    public List<RestaurantModel> findAllRestaurants() {
        return restaurantRepo.findAll();
    }

    public RestaurantModel findRestaurantByID(Long id) {
        if (id < 0) id = 1L;
        return restaurantRepo.findRestaurantModelById(id).orElseThrow(NotFoundException::new);
    }

    public RestaurantModel findRestaurantByAddress(AddressModel adress) {

        return restaurantRepo.findRestaurantModelByAddress(adress).orElseThrow(NotFoundException::new);
    }

    public RestaurantModel findRestaurantByName(String name) {
        return restaurantRepo.findRestaurantModelByName(name).orElseThrow(NotFoundException::new);
    }

    public List<ReviewModel> fetchRestaurantReviews(Long id) {

        return findRestaurantByID(id).getReviews();
    }


    //*UPDATE
    @Transactional
    public RestaurantModel updateRestaurantByID(Long id, Map<String, Object> modelVariables) {
        RestaurantModel restaurant = findRestaurantByID(id);

        return restaurantRepo.save(updateFields(modelVariables, restaurant));
    }

    @Transactional
    public RestaurantModel updateRestaurantByName(String name, Map<String, Object> modelVariables) {
        RestaurantModel restaurant = findRestaurantByName(name);

        return restaurantRepo.save(updateFields(modelVariables, restaurant));
    }

    @Transactional
    public RestaurantModel updateRestaurantFromBody(Map<String, Object> modelVariables) {
        RestaurantModel restaurant = findRestaurantByID((Long) modelVariables.get("id"));

        return restaurantRepo.save(updateFields(modelVariables, restaurant));
    }

    private RestaurantModel updateFields(Map<String, Object> modelVariables, RestaurantModel restaurant) {
        modelVariables.forEach((key, value) -> {
            if (key.equals("id")) return;

            Field field = ReflectionUtils.findField(RestaurantModel.class, key);
            field.setAccessible(true);

            ReflectionUtils.setField(field, restaurant, value);

        });

        return restaurant;
    }


    //*DELETE

    @Transactional
    public void deleteRestaurantByID(Long id) {
        RestaurantModel restaurant = findRestaurantByID(id);
        restaurantRepo.delete(restaurant);
    }
}
