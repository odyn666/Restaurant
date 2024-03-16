package com.github.odyn666.restaurantreservation.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "restaurant")
public class RestaurantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressModel address;
    private LocalTime openTime;
    private LocalTime closeTime;
    @OneToMany(mappedBy = "restaurantModel")
    private List<ImageModel> image;
    @OneToMany(mappedBy = "restaurant",orphanRemoval = true)
    private List<ReviewModel>reviews;

//TODO MULTIPART FILE W CONTROLLER

}
