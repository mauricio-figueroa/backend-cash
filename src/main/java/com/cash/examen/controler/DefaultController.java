package com.cash.examen.controler;

import com.cash.examen.dto.DefaultResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DefaultController {

    private static final String ERROR = "This api has not been developed";

    @RequestMapping
    public ResponseEntity apiNotExist (){
        return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message(ERROR).build(), HttpStatus.BAD_REQUEST);
    }

}
