package com.lettucedream.api.service.impl;

import com.lettucedream.api.repository.UserRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        com.lettucedream.api.model.User user = userRepository.findByUserId(id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }else {
            return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),
                    new ArrayList<>());
        }
    }
}
