package com.lettucedream.api.service.impl;

import com.lettucedream.api.repository.UserRepository;
import com.lettucedream.api.model.User;
import com.lettucedream.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: Serviec class actually performs operations
 * NOTES:
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/

@Service
public class UserServiceImpl implements UserService {


    /**
     * Injecting bean UserRepository
     */
    @Autowired
    private UserRepository UserRepository;
    /**
     * Injecting bean PasswordEncoder
     */
    @Autowired
    private PasswordEncoder bcryptEncoder;

    /**
     * This method inserts a record in the user entity
     * This method also encrypts password using PasswordEncoder helper file
     * @param user
     * @return inserted user
     */
    @Override
    public User addUser(User user) {
        User savedUser =user;
        //Adding bcrypt encryption to encode password in database
        savedUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        UserRepository.saveAndFlush(savedUser);
        return savedUser;
    }

    // TO DO
    /**
     * This method deletes a user record from database
     * @param id
     */
    @Override
    public void delete(long id) {
    }

    /**
     * This method fetches a user record by ID
     * @param id
     * @return user having id supplied in paramerter
     */
    @Override
    public User getById(String id) {
        return UserRepository.findByUserId(id);
    }


    /**
     * This method updates a user
     * @param user
     * @return updated user
     */
    @Override
    public User editUser(User user) {
        return  UserRepository.saveAndFlush(user);
    }

    /**
     * This method fetches all users from user entity
     * @return List of all users
     */
    @Override
    public List<User> getAll() {
        return UserRepository.findAll();
    }

    /**
     * This method checks whether a user having
     * firstname ,lastnme , phone number , date of birth exists
     * @param user
     * @return true if user found else false
     */
    @Override
    public boolean isUserExist(User user) {
        return getByNameBirthday(user.getFirstName(),user.getLastName(),user.getDateOfBirth(),user.getPhoneNumber())!=null;
    }

    /**
     * This method actually checks whether a user exists with
     * firstname ,lastname, dateof birth, phone number
     * @param firstname
     * @param lastname
     * @param dateofBirth
     * @param phonenumber
     * @return null if not found , else user having details supplied in method parameters
     */
    @Override
    public User getByNameBirthday(String firstname, String lastname, Date dateofBirth,long phonenumber ){
        return UserRepository.findByNameBirthday(firstname,lastname,dateofBirth,phonenumber);
    }
}
