package com.cash.examen.service;

import com.cash.examen.dao.LoansDAOImpl;
import com.cash.examen.domain.Loan;
import com.cash.examen.dto.LoansResponseDTO;
import com.cash.examen.utils.LoansBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanService {

    @Autowired
    private LoansDAOImpl loansDAO;

    public LoansResponseDTO getLoans(Integer limit, Integer offset) {
        List<Loan> loans = loansDAO.getLoans(limit, offset);
        return  LoansBuilder.buildLoan(loans,limit,offset,loansDAO.getTotal(limit,offset));
    }
}
