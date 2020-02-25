package com.lettucedream.api.config;

import com.lettucedream.api.model.Attendance;
import com.lettucedream.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {

    @Autowired
    UserService userService;
    @Bean
    @Scope(value = "prototype")
    public Attendance attendance(String userid){
        return new Attendance(userService.getById(userid));
    }
}
