package com.cash.examen.dao;


import java.util.List;

import com.cash.examen.domain.User;

public interface UserDAO {

    List<User> getAllUsers();

    User getUser(long user);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);


}
