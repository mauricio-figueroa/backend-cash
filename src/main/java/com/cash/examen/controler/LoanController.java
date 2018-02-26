package com.cash.examen.controler;

import com.cash.examen.dto.DefaultResponseDTO;
import com.cash.examen.dto.LoansResponseDTO;
import com.cash.examen.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@AllArgsConstructor
public class LoanController {

    private final static String REQUIRED_FIELDS = "Limits and offset parameter required";
    private final static String VALID_PARAMETERS = "Please insert positive Limits and offset";
    private static final String SERVER_CONFLICT = "Conflict on the server, the best engineers are running to fix this";

    @Autowired
    private LoanService loanService;

    @RequestMapping(value = "/loans")
    @ResponseBody
    public ResponseEntity getLoans(@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "offset", required = false) Integer offset, @RequestParam(value = "user_id", required = false) Integer userId) {


        if (!ObjectUtils.allNotNull(limit, offset)) {
            return new ResponseEntity(DefaultResponseDTO.builder().message(REQUIRED_FIELDS).status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }
        log.info("Trying to get loans with limit {}, offset {}", limit, offset);

        if (limit < 0 || offset < 0) {
            return new ResponseEntity(DefaultResponseDTO.builder().message(VALID_PARAMETERS).status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }

        Boolean userIsPresent = userId != null;

        try {
            if (userIsPresent) {
                LoansResponseDTO loans = this.loanService.getLoansFilteredByUserId(limit, offset, userId);
                return new ResponseEntity(loans, HttpStatus.OK);
            } else {
                LoansResponseDTO loans = this.loanService.getLoans(limit, offset);
                return new ResponseEntity(loans, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Error tryng to get Loans ", e);
            return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(SERVER_CONFLICT).build(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


}
