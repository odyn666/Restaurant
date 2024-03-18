package com.github.odyn666.restaurantreservation.model;

import com.github.odyn666.restaurantreservation.utils.AppUserUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@ToString
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
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<UserRole> roles=new ArrayList<>();
    //private AppUserRole appUserRole;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableModel reservedTable;

    @ManyToOne
    private ReservationModel reservation;

    @PrePersist
    void generateUsername() {
        this.creationDate = LocalDateTime.now();
    }
}