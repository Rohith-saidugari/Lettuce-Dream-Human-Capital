package com.lettucedream.api.model.responseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lettucedream.api.model.User;
import com.lettucedream.api.model.enums.AttendaneStatus;
import com.lettucedream.api.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserStatus {
    @Autowired
    @JsonIgnore
    UserService userService;
    private Boolean isUserExist;
    private AttendaneStatus Status;

    UserStatus(){
        this.isUserExist =false;
        this.Status = null;
    }
    public void setUserStatus(User user){
        this.isUserExist=userService.isUserExist(user);
        this.Status=user.getAttendaneStatus();
    }
}
