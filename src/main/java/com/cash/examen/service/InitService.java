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
        Loan loanUser05= Loan.builder().total(new BigDecimal("25000.67")).build();
        Loan loanUser06= Loan.builder().total(new BigDecimal("435433.12")).build();
        Loan loanUser07= Loan.builder().total(new BigDecimal("465456.40")).build();
        Loan loanUser08= Loan.builder().total(new BigDecimal("25000.40")).build();
        Loan loanUser09= Loan.builder().total(new BigDecimal("1234.123")).build();
        Loan loanUser10= Loan.builder().total(new BigDecimal("12354.332")).build();
        Loan loanUser11= Loan.builder().total(new BigDecimal("3453.345")).build();
        Loan loanUser12= Loan.builder().total(new BigDecimal("53443.543")).build();
        Loan loanUser13= Loan.builder().total(new BigDecimal("357645.2")).build();
        Loan loanUser14= Loan.builder().total(new BigDecimal("123123.21")).build();
        Loan loanUser15= Loan.builder().total(new BigDecimal("423.32")).build();
        Loan loanUser16= Loan.builder().total(new BigDecimal("23.43")).build();
        Loan loanUser17= Loan.builder().total(new BigDecimal("55.54")).build();
        Loan loanUser18= Loan.builder().total(new BigDecimal("5645.65")).build();
        Loan loanUser19= Loan.builder().total(new BigDecimal("1123.76")).build();
        Loan loanUser20= Loan.builder().total(new BigDecimal("25000.87")).build();
        Loan loanUser21= Loan.builder().total(new BigDecimal("5453.48")).build();
        Loan loanUser22= Loan.builder().total(new BigDecimal("1235.56")).build();
        Loan loanUser23= Loan.builder().total(new BigDecimal("346.12")).build();
        Loan loanUser24= Loan.builder().total(new BigDecimal("25435.21")).build();

        User user0 = User.builder().first_name("Mauricio").last_name("Figueroa").email("figueroa.a.mj@gmail.com")
                .loans(ImmutableList.of(loanUser00,loanUser01,loanUser02)).build();
        User user1 = User.builder().first_name("Melania").last_name("Miranda").email("melania.smc@gmail.com")
                .loans(ImmutableList.of(loanUser03,loanUser04)).build();
        User user2 = User.builder().first_name("Victor").last_name("Betran").email("victor.betran@gmail.com")
                .loans(ImmutableList.of(loanUser05)).build();
        User user3 = User.builder().first_name("Federico").last_name("Torres").email("federico.torres@gmail.com")
                .loans(ImmutableList.of(loanUser06,loanUser07)).build();
        User user4 = User.builder().first_name("Julian").last_name("Alvarez").email("julian.alvarez@gmail.com")
                .loans(ImmutableList.of(loanUser08,loanUser09)).build();
        User user5 = User.builder().first_name("Mariel").last_name("Cardenas").email("mariel.cardenas@gmail.com")
                .loans(ImmutableList.of(loanUser10,loanUser11)).build();
        User user6 = User.builder().first_name("Jorge").last_name("Santoro").email("jorge.santoro@gmail.com")
                .loans(ImmutableList.of(loanUser12,loanUser13)).build();
        User user7 = User.builder().first_name("Javier").last_name("Sandoval").email("javier.sandoval@gmail.com")
                .loans(ImmutableList.of(loanUser14,loanUser15)).build();
        User user8 = User.builder().first_name("Carmen").last_name("Barbieri").email("carmen.barbieri@gmail.com")
                .loans(ImmutableList.of(loanUser16,loanUser17,loanUser18,loanUser19)).build();
        User user9 = User.builder().first_name("Mariana").last_name("Tellio").email("mariana.tellio@gmail.com")
                .loans(ImmutableList.of(loanUser20,loanUser21,loanUser22,loanUser23,loanUser24)).build();


      //  userDAO.save(user0);
      //  userDAO.save(user1);
      //  userDAO.save(user2);
        userDAO.save(user3);
        userDAO.save(user4);
        userDAO.save(user5);
        userDAO.save(user6);
        userDAO.save(user7);
        userDAO.save(user8);
        userDAO.save(user9);
    }



}
