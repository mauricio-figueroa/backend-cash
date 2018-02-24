package com.cash.examen.controler;

import com.cash.examen.domain.User;
import com.cash.examen.dto.DefaultResponseDTO;
import com.cash.examen.exception.UserAlreadyRegisteredException;
import com.cash.examen.service.InitService;
import com.cash.examen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    private static final String USER_HAS_BEEN_CREATED = "The user has been created";
    private static final String SERVER_CONFLICT = "Conflict on the server, the best engineers are running to fix this";

    @Autowired
    private UserService userService;

    @Autowired
    private InitService initService;

    @Autowired
    private GsonHttpMessageConverter gsonConverter;


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
    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public Object getUserById(@PathVariable Integer id) {

        if (ObjectUtils.allNotNull(id)) {
            log.info("trying to find User info for id {}", id);
            return userService.findUser(id);
        }

        log.info("id can not be null");

        return HttpStatus.CONFLICT;
    }

    @RequestMapping(value = ("/usersss"), method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(HttpEntity<String> httpEntity) {

        try {
            User newUser = gsonConverter.getGson().fromJson(httpEntity.getBody(), User.class);
            userService.createUser(newUser);
            return new ResponseEntity<>(DefaultResponseDTO.builder().status(HttpStatus.OK).message(USER_HAS_BEEN_CREATED).build(), HttpStatus.CREATED);
        } catch (UserAlreadyRegisteredException e) {
            log.error("Error", e);
            return new ResponseEntity<>(DefaultResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error", e);
            return new ResponseEntity<>(DefaultResponseDTO.builder().status(HttpStatus.CONFLICT).message(SERVER_CONFLICT).build(), HttpStatus.CONFLICT);
        }

    }
}