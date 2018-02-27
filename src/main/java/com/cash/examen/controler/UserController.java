package com.cash.examen.controler;

import com.cash.examen.domain.User;
import com.cash.examen.dto.DefaultResponseDTO;
import com.cash.examen.exception.UserAlreadyRegisteredException;
import com.cash.examen.service.InitService;
import com.cash.examen.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController {

    private static final String USER_CREATED = "The user has been created";
    private static final String SERVER_CONFLICT = "Conflict on the server, the best engineers are running to fix this";
    private static final String ID_CAN_NOT_BE_NULL = "Id can not be null";
    private static final String PLEASE_SPECIFY_USER_ID = "Please specify the user id";
    private static final String USER_NOT_EXIST = "User id not exist";
    private static final String USER_DELETED = "User has been deleted";

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

    @RequestMapping(value = "users/", method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message(PLEASE_SPECIFY_USER_ID).build(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable Integer id) {

        if (!ObjectUtils.allNotNull(id)) {
            log.info("id can not be null");
            return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message(ID_CAN_NOT_BE_NULL).build(), HttpStatus.BAD_REQUEST);
        }

        final User user = userService.findUser(id);
        if (!ObjectUtils.allNotNull(user)) {
            log.info("User with id {} not exist", id);
            return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.CONFLICT).message(USER_NOT_EXIST).build(), HttpStatus.CONFLICT);
        }

        try {
            log.info("Trying to find User info for id {}", id);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(SERVER_CONFLICT).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable Integer id) {

        if (!ObjectUtils.allNotNull(id)) {
            log.info("id can not be null");
            return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message(ID_CAN_NOT_BE_NULL).build(), HttpStatus.BAD_REQUEST);
        }

        final User user = userService.findUser(id);
        if (!ObjectUtils.allNotNull(user)) {
            log.info("User with id {} not exist", id);
            return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.CONFLICT).message(USER_NOT_EXIST).build(), HttpStatus.CONFLICT);
        }

        try {
            log.info("Trying to delete User info for id {}", id);
            userService.deleteUser(id);
            return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.OK).message(USER_DELETED).build(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error",e);
            return new ResponseEntity(DefaultResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(SERVER_CONFLICT).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = ("/users"), method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody User user) {
        log.info("Trying to create user email {}", user.getEmail());
        try {
            userService.createUser(user);
            log.info("Create user with email {}", user.getEmail());
            return new ResponseEntity<>(DefaultResponseDTO.builder().status(HttpStatus.OK).message(USER_CREATED).build(), HttpStatus.CREATED);
        } catch (UserAlreadyRegisteredException e) {
            log.error("User {} already exist",user.getEmail() , e);
            return new ResponseEntity<>(DefaultResponseDTO.builder().status(HttpStatus.CONFLICT).message(e.getMessage()).build(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Error", e);
            return new ResponseEntity<>(DefaultResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(SERVER_CONFLICT).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}