package com.cash.examen.controler;

import com.cash.examen.domain.Loan;
import com.cash.examen.dto.DefaultResponseDTO;
import com.cash.examen.dto.LoansResponseDTO;
import com.cash.examen.service.LoanService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoanController {

    private final static String REQUIRED_FIELDS = "Limits and offset parameter required";
    private final static String VALID_PARAMETERS = "Please insert positive Limits and offset";

    @Autowired
    private LoanService loanService;

    @RequestMapping(value = "/loans")
    @ResponseBody
    public ResponseEntity getLoans(@RequestParam(value = "limit",required = false) Integer limit, @RequestParam(value = "offset",required = false) Integer offset, @RequestParam(value = "user" ,required = false) Integer user) {

        if(!ObjectUtils.allNotNull(limit,offset)){
            return new ResponseEntity(DefaultResponseDTO.builder().message(REQUIRED_FIELDS).status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }

        if(limit<0 || offset< 0 ){
            return new ResponseEntity(DefaultResponseDTO.builder().message(REQUIRED_FIELDS).status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }

        Boolean userIsPresent = user!=null;

        if(userIsPresent){

        }else{
            LoansResponseDTO loans = this.loanService.getLoans(limit, offset);
           return new ResponseEntity(loans, HttpStatus.OK);
        }



        return new ResponseEntity(HttpStatus.OK);
    }




}
