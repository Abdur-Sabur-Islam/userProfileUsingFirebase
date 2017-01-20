package com.bproperty.userprofileusingfirebase;

/**
 * Created by acer on 1/20/2017.
 */

public class User {
    public String userName;
    public String userEmail;

    public User() {
    }

    public User(String userName, String userEmail) {
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
