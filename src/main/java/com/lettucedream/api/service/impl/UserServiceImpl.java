package com.lettucedream.api.service.impl;

import com.lettucedream.api.repository.UserRepository;
import com.lettucedream.api.model.User;
import com.lettucedream.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public User addUser(User user) {
        User savedUser = UserRepository.saveAndFlush(user);
        return savedUser;
    }


    // TO DO
    @Override
    public void delete(long id) {

    }




    @Override
    public User getById(String id) {
        return UserRepository.findByUserId(id);
    }


    // TO DO
    @Override
    public User editUser(User user) {
        return  UserRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return UserRepository.findAll();
    }

    @Override
    public boolean isUserExist(User user) {
        return getByNameBirthday(user.getFirstName(),user.getLastName(),user.getDateOfBirth(),user.getPhoneNumber())!=null;
    }

    @Override
    public User getByNameBirthday(String firstname, String lastname, Date dateofBirth,long phonenumber ){
        return UserRepository.findByNameBirthday(firstname,lastname,dateofBirth,phonenumber);
    }
}
