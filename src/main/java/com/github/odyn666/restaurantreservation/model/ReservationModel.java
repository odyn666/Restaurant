package com.github.odyn666.restaurantreservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class ReservationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy ="reservation")
    private List<AppUserModel>reservedBy;
    @OneToOne
    private TableModel reservedTable;
    private ZonedDateTime reservationDate;

}
