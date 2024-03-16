package com.github.odyn666.restaurantreservation.service;

import com.github.odyn666.restaurantreservation.dto.UserRegistrationDto;
import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.model.AppUserRole;
import com.github.odyn666.restaurantreservation.repository.AppUserRepository;
import com.github.odyn666.restaurantreservation.service.exception.NotFoundException;
import com.github.odyn666.restaurantreservation.service.mapper.AppUserMapper;
import com.github.odyn666.restaurantreservation.utils.AppUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    //* CREATE
    @Transactional
    public AppUserModel createUser(UserRegistrationDto dto) {
        //TODO CHECK IF USER EXISTS

        AppUserModel entity = AppUserMapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepo.save(entity);
    }

    //*READ
    public List<AppUserModel> findAllUsers() {
        return userRepo.findAll();
    }

    public AppUserModel findUserByID(Long id) {
        if (id < 0) id = 1L;
        return userRepo.findAppUserModelById(id).orElseThrow(NotFoundException::new);
    }

    public List<AppUserModel> findUsersByRole(AppUserRole role) {
        if (!(role == null)) role = AppUserRole.USER;
        return userRepo.findAppUserModelByAppUserRole(role).orElseThrow(NotFoundException::new);
    }

    public AppUserModel findUserByUsername(String username) {
        return userRepo.findAppUserModelByUsername(username).orElseThrow(NotFoundException::new);
    }

    public AppUserModel findUserByEmail(String email) {
        return userRepo.findAppUserModelByEmail(email).orElseThrow(NotFoundException::new);
    }


    //*UPDATE
    @Transactional
    public AppUserModel updateUserByID(Long id, Map<String, Object> modelVariables) {
        AppUserModel user = findUserByID(id);

        return userRepo.save(updateFields(modelVariables, user));
    }

    @Transactional
    public AppUserModel updateUserByUsername(String username, Map<String, Object> modelVariables) {
        AppUserModel user = findUserByUsername(username);

        return userRepo.save(updateFields(modelVariables, user));
    }

    @Transactional
    public AppUserModel updateUserFromBody(Map<String, Object> modelVariables) {
        AppUserModel user = findUserByID((Long) modelVariables.get("id"));

        return userRepo.save(updateFields(modelVariables, user));
    }

    private AppUserModel updateFields(Map<String, Object> modelVariables, AppUserModel user) {
        modelVariables.forEach((key, value) -> {
            if (key.equals("id")) return;

            Field field = ReflectionUtils.findField(AppUserModel.class, key);
            field.setAccessible(true);

            if (field.getType().isEnum() && field.getType().equals(AppUserRole.class)) {
                // Handle enum (AppUserRole) separately
                AppUserRole userRole = AppUserRole.valueOf((String) value);
                ReflectionUtils.setField(field, user, userRole);
            } else {
                ReflectionUtils.setField(field, user, value);
            }
        });
        AppUserUtil util = new AppUserUtil();
        user.setUsername(util.createUsername(user.getUsername(), user.getLastname()));

        return user;
    }


    //*DELETE

    @Transactional
    public void deleteUser(Long id) {
        AppUserModel user = findUserByID(id);
        userRepo.delete(user);
    }
}
