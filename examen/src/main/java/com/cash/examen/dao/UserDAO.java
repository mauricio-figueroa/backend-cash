package com.cash.examen.dao;


import java.util.List;

import com.cash.examen.domain.User;

public interface UserDAO {

    List<User> getAllUsers();

    User getUser(Integer userId);

    void save(User user);

    void updateUser(User user);

    void deleteUser(User user);

    boolean emailAlreadyRegistred(String email);


}
