package com.lettucedream.api.controller;


import com.lettucedream.api.model.Attendance;
import com.lettucedream.api.model.User;
import com.lettucedream.api.model.enums.AttendaneStatus;
import com.lettucedream.api.model.responseModel.UserStatus;
import com.lettucedream.api.repository.AttendanceRepository;
import com.lettucedream.api.service.AttendanceService;
import com.lettucedream.api.service.UserService;
import com.lettucedream.api.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user/")
public class AttendanceController {


    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserStatus userStatus;
    @Autowired
    CustomErrorType customErrorType;
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    BeanFactory factory;



    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<?> verifyUser(@RequestParam String userId, UriComponentsBuilder ucBuilder) {
        logger.info("Verifying  Userid : {}", userId);
        if (userService.getById(userId) != null) {
            userStatus.setUserStatus(userService.getById(userId));
            return  ResponseEntity.ok()
                    .body(userStatus);
        } else {
            customErrorType.setErrorMessage("The user  id    "+ userId +"   does not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(customErrorType);
        }
    }




    @RequestMapping(value ="/clockin",method = RequestMethod.POST)
    public ResponseEntity<?> clockin(@RequestParam String userId, @RequestParam String clockin_time) throws ParseException {
        User user = userService.getById(userId);
        if (!Objects.nonNull(user)){
            customErrorType.setErrorMessage("The user  id    "+ userId +"   does not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(customErrorType);
        }
        else if (user.getAttendaneStatus().equals(AttendaneStatus.COMPLETE)) {
            logger.info("clock in   Userid : {} , Time : {}", userId, clockin_time);
            Attendance attendance = (Attendance)  factory.getBean(Attendance.class,userId);
            SimpleDateFormat dateformat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            attendance.setClockInTime(new java.sql.Date(dateformat.parse(clockin_time).getTime()));
            attendanceService.addAttendance(attendance);
            user.setAttendaneStatus(AttendaneStatus.INCOMPLETE);
            userService.editUser(user);
            return ResponseEntity.ok().body(attendance);
        }
        else {
            customErrorType.setErrorMessage("You have missed punches , please correct them before clocking in ");
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(customErrorType);
        }

    }

    @RequestMapping(value="/clockout", method = RequestMethod.POST)
    public ResponseEntity<?> clockout(@RequestParam String userId, @RequestParam String clockout_Time) throws ParseException {
        User user = userService.getById(userId);
        if (!Objects.nonNull(user)){
            customErrorType.setErrorMessage("The user  id    "+ userId +"   does not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(customErrorType);
        }
        else if (user.getAttendaneStatus().equals(AttendaneStatus.INCOMPLETE)) {
            logger.info("clock out  Userid : {} , Time : {}", userId, clockout_Time);
            Attendance attendance = attendanceService.getLastUpdatedRecord(userId);
            SimpleDateFormat dateformat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            attendance.setClockOutTime(new java.sql.Date(dateformat.parse(clockout_Time).getTime()));
            attendanceService.editAttendance(attendance);
            user.setAttendaneStatus(AttendaneStatus.COMPLETE);
            userService.editUser(user);
            return ResponseEntity.ok().body(attendance);
        }
        else {
            customErrorType.setErrorMessage("You Do not have any punch inss ");
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(customErrorType);
        }

    }
}
