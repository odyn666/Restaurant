package com.github.odyn666.restaurantreservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "_table")
public class TableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private Short tableNumber;
    private Boolean isReserved;
    @OneToMany(mappedBy = "reservedTable",orphanRemoval = true)
    private List<AppUserModel> reservedBy;


}
