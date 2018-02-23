package com.cash.examen.controler;

import com.cash.examen.service.InitService;
import com.cash.examen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private InitService initService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/init-exam", method = RequestMethod.GET)
    public void initexam() {
        log.info("Init Exam!");
        initService.initService();
    }


    //todo object no
    @RequestMapping(value="users/{id}", method=RequestMethod.GET)
    public Object adminStudent(@PathVariable Integer id) {

        if(ObjectUtils.allNotNull(id)){
            log.info("trying to find User info for id {}",id);
            return userService.findUser(id);
        }

        log.info("id can not be null");


        return HttpStatus.CONFLICT.name();
    }


}