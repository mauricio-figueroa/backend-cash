package com.cash.examen.service;

import com.cash.examen.dao.UserDAOImpl;
import com.cash.examen.domain.Loan;
import com.cash.examen.domain.User;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class InitService {

    @Autowired
    private UserDAOImpl userDAO;

    public void initService(){
        Loan loanUser00= Loan.builder().total(new BigDecimal("42.40")).build();

        User user0 = User.builder().first_name("Mauricio").last_name("Figueroa").email("figueroa.a.mj@gmail.com").loans(ImmutableList.of(loanUser00)).build();
        User user1 = User.builder().first_name("Melania").last_name("Miranda").email("melania.smc@gmail.com").build();
        User user2 = User.builder().first_name("Victor").last_name("Betran").email("victor.betran@gmail.com").build();

        userDAO.save(user0);
        userDAO.save(user1);
        userDAO.save(user2);
    }



}
