package com.github.odyn666.restaurantreservation.utils;

public class AppUserUtil {

    public  String createUsername(String firstname,String lastName){
        return firstname.charAt(0)+"."+lastName;
    }

}
