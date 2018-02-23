package com.cash.examen.service;

import com.cash.examen.dao.UserDAOImpl;
import com.cash.examen.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserDAOImpl userDAO;

    public User findUser(Integer id) {
        try {
            return userDAO.getUser(id);
        } catch (Exception e) {
            return null;
        }
    }

}
