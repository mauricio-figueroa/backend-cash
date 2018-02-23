package com.cash.examen.service;

import com.cash.examen.dao.UserDAOImpl;
import com.cash.examen.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitService {

    @Autowired
    private UserDAOImpl userDAO;

    public void initService(){


        User user0 = new User();
        user0.setFirst_name("Mauricio");
        user0.setLast_name("Figueroa");
        user0.setEmail("figueroa.a.mj@gmail.com");

        userDAO.addUser(user0);
    }



}
