package com.github.odyn666.restaurantreservation.controller;

import com.github.odyn666.restaurantreservation.model.ImageModel;
import com.github.odyn666.restaurantreservation.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/get/all")
    public ResponseEntity<List<ImageModel>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ImageModel> getImageByID(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImageByID(id));
    }


    @PostMapping("/add")
    public ResponseEntity<ImageModel> createImage(@RequestBody ImageModel imageModel, @RequestParam("imageFile") MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            imageModel.setImage(bytes);
            return ResponseEntity.ok(imageService.createImage(imageModel));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<ImageModel> updateImageByID(@PathVariable Long id, @RequestBody Map<String ,Object> imageModel) {
        return ResponseEntity.ok(imageService.updateImageByID(id, imageModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImageByID(@PathVariable Long id) {
        imageService.deleteImageByID(id);
        return ResponseEntity.ok().build();
    }
}
