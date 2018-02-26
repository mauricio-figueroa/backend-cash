package com.cash.examen.controler;

import com.cash.examen.domain.Loan;
import com.cash.examen.dto.DefaultResponseDTO;
import com.cash.examen.dto.LoansResponseDTO;
import com.cash.examen.service.LoanService;
import com.cash.examen.utils.LoansResponseBuilder;
import com.google.common.collect.ImmutableList;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

public class LoanControllerTest {

    @Mock
    private LoanService loanService;


    private LoanController target;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        target = new LoanController(loanService);
    }

    @Test
    public void get_loans_negative_limit() {
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message("Please insert positive Limits and offset").build();

        ResponseEntity response = target.getLoans(-1, 1, 1);
        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get_loans_negative_offset() {
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message("Please insert positive Limits and offset").build();

        ResponseEntity response = target.getLoans(1, -1, 1);
        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get_loans_negative_offset_and_limit() {
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message("Please insert positive Limits and offset").build();

        ResponseEntity response = target.getLoans(-1, -1, 1);
        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void get_loans_user_present() {

        Integer limit = 11;
        Integer offset = 4;
        Integer userId = 1;

        Loan loan0 = Loan.builder().user_id(1).id(1).total(new BigDecimal("33.40")).build();
        Loan loan1 = Loan.builder().user_id(2).id(2).total(new BigDecimal("36.40")).build();
        Loan loan2 = Loan.builder().user_id(2).id(3).total(new BigDecimal("2363.40")).build();
        Loan loan3 = Loan.builder().user_id(3).id(4).total(new BigDecimal("7890.40")).build();

        LoansResponseDTO expected = LoansResponseBuilder.buildLoan(ImmutableList.of(loan0, loan1, loan2, loan3), limit, offset, new Integer(12));
        when(loanService.getLoansFilteredByUserId(limit, offset, userId)).thenReturn(expected);

        ResponseEntity response = target.getLoans(limit, offset, userId);
        LoansResponseDTO actual = (LoansResponseDTO) response.getBody();

        Assert.assertEquals(actual, expected);
    }


}