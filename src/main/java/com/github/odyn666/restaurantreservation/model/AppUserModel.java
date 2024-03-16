package com.github.odyn666.restaurantreservation.model;

import com.github.odyn666.restaurantreservation.utils.AppUserUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class AppUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private AppUserRole appUserRole;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableModel reservedTable;

    @ManyToOne
    private ReservationModel reservation;

    @PrePersist
    void generateUsername() {
        AppUserUtil util = new AppUserUtil();
        this.creationDate = LocalDateTime.now();
        username = util.createUsername(getFirstname(), getLastname());
    }
}