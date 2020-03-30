package com.lettucedream.api.service.impl;




import com.lettucedream.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        com.lettucedream.api.model.User user = userService.getById(id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }else {

            //Setup authorities and return userdetails service object
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));
            return new org.springframework.security.core.userdetails.User(user.getUser_id(), user.getPassword(),
                    grantedAuthorities);
        }
    }
}
