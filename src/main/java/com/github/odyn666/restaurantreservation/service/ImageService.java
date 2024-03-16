package com.github.odyn666.restaurantreservation.service;

import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.model.AppUserRole;
import com.github.odyn666.restaurantreservation.model.ImageModel;
import com.github.odyn666.restaurantreservation.repository.ImageRepository;
import com.github.odyn666.restaurantreservation.utils.AppUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageModel createImage(ImageModel imageModel) {
        return imageRepository.save(imageModel);
    }

    public List<ImageModel> getAllImages() {
        return imageRepository.findAll();
    }

    public ImageModel getImageByID(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public ImageModel updateImageByID(Long id, Map<String, Object> modelVariables) {
        ImageModel image = getImageByID(id);
        return imageRepository.save(updateFields(modelVariables, image));
    }

    private ImageModel updateFields(Map<String, Object> modelVariables, ImageModel image) {
        modelVariables.forEach((key, value) -> {
            if (key.equals("id")) return;

            Field field = ReflectionUtils.findField(ImageModel.class, key);
            field.setAccessible(true);

            ReflectionUtils.setField(field, image, value);

        });

        return image;
    }

    public void deleteImageByID(Long id) {
        ImageModel image = getImageByID(id);
        imageRepository.delete(image);
    }

}
