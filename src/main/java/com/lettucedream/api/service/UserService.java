package com.lettucedream.api.service;

import com.lettucedream.api.model.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User addUser(User user);
    void delete(long id);

    User getById(String id);
    User editUser(User user);
    List<User> getAll();
    boolean isUserExist(User user);
    User getByNameBirthday(String firstname, String lastname, Date dateofBirth, long phonenumber);
}
