package com.lettucedream.api.model;

import java.io.Serializable;
/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: This model is a part of authetication getter and setters has bee used in
 * JWT token generation and verification.
 * I dont want to store any token in DB , thats the reason why you dont see @Entity @Id in this class
 * NOTES: Its just a helper class model
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String userId;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public JwtRequest(String userId, String password) {
        this.setUserId(userId);
        this.setPassword(password);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
