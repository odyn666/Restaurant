package com.github.odyn666.restaurantreservation.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class ReservationModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy ="reservation")
    private List<AppUserModel>reservedBy;
    @OneToOne
    private TableModel reservedTable;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationDate;


}
