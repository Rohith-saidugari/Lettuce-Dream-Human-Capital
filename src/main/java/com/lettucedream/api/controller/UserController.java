package com.lettucedream.api.controller;


import com.lettucedream.api.model.User;

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
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService UserService;
    @Autowired
    CustomErrorType customErrorType;
    @Autowired
    ZXingHelper zXingHelper;

    // Create user Either returns Image or Error message
    @RequestMapping(value = "/createuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder, HttpServletResponse response) throws IOException {
        logger.info("Creating User : {}", user);
        if (UserService.isUserExist(user)) {
            logger.error("Unable to create. A User with name {}  {} phone number {} already exist", user.getFirstName(), user.getLastName(), user.getPhoneNumber());
            customErrorType.setErrorMessage("Unable to create. A User with following details already exist : Full name:" +
                    user.getFirstName() + " " + user.getLastName() + ", Date Of Birth :" + user.getDateOfBirth() + ", Phone Number :" + user.getPhoneNumber() + " user id :"
                    + UserService.getByNameBirthday(user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getPhoneNumber()).getUser_id());
            return ResponseEntity.status(HttpStatus.CONFLICT).
                    body(customErrorType);
        }

        HttpHeaders headers = new HttpHeaders();
        User createdUser = UserService.addUser(user);
        setbarcodeImageData(createdUser);
        //createdUser.setBase64EncodedImage(Base64.getEncoder().encodeToString(zXingHelper.getBarCodeImage(createdUser.getUser_id(),500,100)));
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getUser_id()).toUri());
        return ResponseEntity.ok()
                .headers(headers)
                .body(createdUser);
    }


    // Update user profile (Phone Number and Address )

    @RequestMapping(value = "/getuser/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        logger.info("Updating User with id {}", id);
        if (UserService.getById(id) == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            customErrorType.setErrorMessage("Unable to upate. User with id " + id + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(customErrorType);
        }
        //Cannot Change Name Date of Birth
        User currentUser = UserService.getById(id);
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setStreetAddress(user.getStreetAddress());
        currentUser.setCity(user.getCity());
        currentUser.setState(user.getState());
        currentUser.setZipCode(user.getZipCode());
        UserService.editUser(currentUser);
        setbarcodeImageData(currentUser);
        return ResponseEntity.ok()
                .body(currentUser);
    }


    //Get all users
    @RequestMapping(value = "/reportdata", method = RequestMethod.GET)

    public ResponseEntity<?> listAllUsers() {
        List<User> users = UserService.getAll();
        if (users.isEmpty()) {
            customErrorType.setErrorMessage("Unable to fetch report data , No report data not found.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(customErrorType);
        }
        for (User user : users) {
            setbarcodeImageData(user);
        }
        return ResponseEntity.ok().body(users);
    }


    private void setbarcodeImageData(User user) {
        user.setBase64EncodedImage(Base64.getEncoder().encodeToString(zXingHelper.getBarCodeImage(user.getUser_id(), 500, 100)));
    }

}
