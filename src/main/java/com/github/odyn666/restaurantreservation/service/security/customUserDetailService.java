package com.github.odyn666.restaurantreservation.service.security;

import com.github.odyn666.restaurantreservation.model.AppUserModel;
import com.github.odyn666.restaurantreservation.model.UserRole;
import com.github.odyn666.restaurantreservation.repository.AppUserRepository;
import com.github.odyn666.restaurantreservation.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class customUserDetailService implements UserDetailsService {

    private final AppUserRepository userRepo;
    private final AppUserService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUserModel appUserModel = service.findUserByEmail(email);
        if (appUserModel != null) {
            return new org.springframework.security.core.userdetails.User(appUserModel.getUsername(),
                    appUserModel.getPassword(),mapRolesToAuthorities(appUserModel.getRoles()));
        }


        return null;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<UserRole> roles) {
        Collection<? extends  GrantedAuthority>mapRoles=roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).toList();


        return mapRoles;
    }
}
