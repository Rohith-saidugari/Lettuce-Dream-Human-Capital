package com.lettucedream.api.controller;


import com.lettucedream.api.model.User;
import com.lettucedream.api.model.enums.AttendaneStatus;
import com.lettucedream.api.service.UserService;
import com.lettucedream.api.util.CustomErrorType;
import com.lettucedream.api.util.ZXingHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService UserService;
    @Autowired
    ZXingHelper zXingHelper;

    //Get all users
    @RequestMapping(value = "/users", method = RequestMethod.GET)

    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = UserService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    // Create user Either returns Image or Error message
    @RequestMapping(value = "/users", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder, HttpServletResponse response) throws IOException {
        logger.info("Creating User : {}", user);
        if (UserService.isUserExist(user)) {
            logger.error("Unable to create. A User with name {}  {} phone number {} already exist", user.getFirstName(),user.getLastName(),user.getPhoneNumber());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with following details already exist : Full name:" +
                    user.getFirstName()+" "+user.getLastName()+", Date Of Birth :"+user.getDateOfBirth() +", Phone Number :"+user.getPhoneNumber() +" user id :"
                    +UserService.getByNameBirthday(user.getFirstName(),user.getLastName(),user.getDateOfBirth(),user.getPhoneNumber()).getUser_id() ),HttpStatus.CONFLICT);
        }
        String id =UserService.addUser(user).getUser_id();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getUser_id()).toUri());
        response.setContentType("image/png");
        response.setHeader("headers",id);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(zXingHelper.getBarCodeImage(id, 500, 100));
        outputStream.flush();
        outputStream.close();
        return ResponseEntity.ok()
                .headers(headers)
                .body(zXingHelper.getBarCodeImage(id, 500, 100));
        //return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


    // Update user profile (Phone Number and Address )

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        logger.info("Updating User with id {}", id);

        User currentUser = UserService.getById(id);

        if (currentUser == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        //Cannot Change Name Date of Birth
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setStreetAddress(user.getStreetAddress());
        currentUser.setCity(user.getCity());
        currentUser.setState(user.getState());
        currentUser.setZipCode(user.getZipCode());
        UserService.editUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }


    // Verify user ID

    //TODO need to change response body content

    @RequestMapping(value="/users/verify", method=RequestMethod.POST)

    public ResponseEntity<?> verifyUser(@RequestParam String userId, UriComponentsBuilder ucBuilder){
        logger.info("Verifying  Userid : {}", userId);
        if(UserService.getById(userId)!=null){
            return new ResponseEntity<String>("Found",HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("NotFound",HttpStatus.OK);
    }



    // TODO
    @RequestMapping("/users/clockin")
    public ResponseEntity<?> clockin(@RequestParam String userId, @RequestParam Date clockin_time, UriComponentsBuilder ucBuilder){
        logger.info("clock in   Userid : {} , Time : {}", userId,clockin_time);
        //Clock in if previous clock out was successful;
        if(UserService.getById(userId).getAttendaneStatus().equals(AttendaneStatus.COMPLETE))
            return null;
        else return null;
    }





}
