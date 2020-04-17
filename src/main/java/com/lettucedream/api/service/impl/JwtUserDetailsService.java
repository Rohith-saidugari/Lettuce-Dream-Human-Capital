package com.lettucedream.api.service.impl;




import com.lettucedream.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: service class actually performs operations
 * NOTES:
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
@Service
public class JwtUserDetailsService implements UserDetailsService {


    /**
     * Injecting UserService bean
     */
    @Autowired
    private UserService userService;

    /**
     * This method fetches a user by user ID , if user id not found it throws an exception
     * If user id found its sets user authorities returns user with set of authorities
     * for authentication purpose
     * @param id (userID)
     * @return User with set of authorities
     * @throws UsernameNotFoundException
     */
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
