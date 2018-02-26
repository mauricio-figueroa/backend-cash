package com.cash.examen.service;

import com.cash.examen.dao.UserDAOImpl;
import com.cash.examen.domain.User;
import com.cash.examen.exception.UserAlreadyRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserService {

    private final static String EMAIL_ALREADY_REGISTERED = "The email is already registered!, please insert a new mail";

    @Autowired
    private UserDAOImpl userDAO;

    public User findUser(Integer id) {
            return userDAO.getUser(id);
    }

    public void createUser(User newUser) throws UserAlreadyRegisteredException {

        if (userDAO.emailAlreadyRegistred(newUser.getEmail())) {
            throw new UserAlreadyRegisteredException(EMAIL_ALREADY_REGISTERED);
        }
        userDAO.save(newUser);
    }

    public void deleteUser(Integer id) {
        User user = userDAO.getUser(id);
        userDAO.deleteUser(user);
    }
}
