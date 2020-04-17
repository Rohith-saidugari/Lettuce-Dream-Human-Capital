package com.lettucedream.api.config;

import com.lettucedream.api.model.Attendance;
import com.lettucedream.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: Configuration file
 * To return a prototype bean  of type attendance
 * NOTES: ref https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch04s04.html
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
@Configuration
public class BeanConfig {

    @Autowired
    UserService userService;
    @Bean
    @Scope(value = "prototype")
    /**
     *  method creates a prototype bean of attendance type
     */
    public Attendance attendance(String userid){
        return new Attendance(userService.getById(userid));
    }
}
