package com.cash.examen.utils;

import com.cash.examen.domain.Loan;
import com.cash.examen.domain.PaginDTO;
import com.cash.examen.dto.LoansResponseDTO;

import java.util.List;

public class LoansBuilder {


    public static LoansResponseDTO buildLoan(List<Loan> loans, Integer limit, Integer offset, Integer total) {
        return LoansResponseDTO.builder().items(loans).pagin(PaginDTO.builder().limit(limit).offset(offset).total(total).build()).build();
    }
}
