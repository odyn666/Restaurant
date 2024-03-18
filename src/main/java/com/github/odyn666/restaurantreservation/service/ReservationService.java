package com.github.odyn666.restaurantreservation.service;

import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.model.ReservationModel;
import com.github.odyn666.restaurantreservation.model.TableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final AppUserService userService;
    private final TableService tableService;


    public void createReservation(TableModel table, Date reservationDate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUserModel user = userService.findUserByUsername(authentication.getName());
        ReservationModel reservation = ReservationModel.builder()
                .reservedBy(table.getReservedBy())
                .reservedTable(table)
                .reservationDate(reservationDate)
                .build();

        System.out.println(authentication.getName());
        System.out.println(user.getFirstname() + "  " + user.getLastname());

    }

    private List<AppUserModel> getReservationUserList(AppUserModel... user) {
        if (user.length == 0)
            throw new IllegalArgumentException("User cannot be empty");


        return Arrays.asList(user);
    }
}
