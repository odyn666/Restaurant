package com.github.odyn666.restaurantreservation.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image_model")
public class ImageModel {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private byte[] image;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurantModel;
}
