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
        Loan loanUser01= Loan.builder().total(new BigDecimal("33.40")).build();
        Loan loanUser02= Loan.builder().total(new BigDecimal("21.40")).build();
        Loan loanUser03= Loan.builder().total(new BigDecimal("890.40")).build();
        Loan loanUser04= Loan.builder().total(new BigDecimal("43.40")).build();
        Loan loanUser05= Loan.builder().total(new BigDecimal("25000.40")).build();

        User user0 = User.builder().first_name("Mauricio").last_name("Figueroa").email("figueroa.a.mj@gmail.com")
                .loans(ImmutableList.of(loanUser00,loanUser01,loanUser02)).build();
        User user1 = User.builder().first_name("Melania").last_name("Miranda").email("melania.smc@gmail.com")
                .loans(ImmutableList.of(loanUser03,loanUser04)).build();
        User user2 = User.builder().first_name("Victor").last_name("Betran").email("victor.betran@gmail.com")
                .loans(ImmutableList.of(loanUser05)).build();

        userDAO.save(user0);
        userDAO.save(user1);
        userDAO.save(user2);
    }



}
