package com.Dao;

import com.model.User;

public interface UserDao {
     boolean addUser(User user);
    boolean isValidUser(String username, String password);
    boolean isValidRegister(String email, String password);
    boolean isValidEmail(String email);

}
