package com.cash.examen.controler;

import com.cash.examen.domain.User;
import com.cash.examen.dto.DefaultResponseDTO;
import com.cash.examen.exception.UserAlreadyRegisteredException;
import com.cash.examen.service.InitService;
import com.cash.examen.service.UserService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@Test
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private InitService initService;

    private UserController target;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        target = new UserController(userService, initService);
    }

    @Test
    public void user_not_exist() {
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.CONFLICT).message("User id not exist").build();

        Integer id = new Integer(3);
        when(userService.findUser(id)).thenReturn(null);

        ResponseEntity response = target.getUserById(id);
        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void user_exist() {
        Integer id = new Integer(3);
        User user = User.builder().first_name("Mauricio").last_name("Figueroa").email("figueroa.a.mj@gmail.com").build();

        when(userService.findUser(id)).thenReturn(user);
        ResponseEntity response = target.getUserById(id);

        User actual = (User) response.getBody();
        Assert.assertEquals(actual, user);
    }


    @Test
    public void find_user_throw_exception() {
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Conflict on the server, the best engineers are running to fix this").build();
        Integer id = new Integer(3);
        User user = User.builder().first_name("Mauricio").last_name("Figueroa").email("figueroa.a.mj@gmail.com").build();

        when(userService.findUser(id)).thenReturn(user).thenThrow(new RuntimeException());
        ResponseEntity response = target.getUserById(id);

        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void delete_user_ok() {
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.OK).message("User has been deleted").build();
        Integer id = new Integer(3);
        ResponseEntity response = target.deleteUser(id);

        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void delete_user_throws_exception() {
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Conflict on the server, the best engineers are running to fix this").build();
        Integer id = new Integer(3);

        doThrow(new RuntimeException()).when(userService).deleteUser(id);
        ResponseEntity response = target.deleteUser(id);

        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void create_user_ok() {
        User user = User.builder().first_name("Mauricio").last_name("Figueroa").email("figueroa.a.mj@gmail.com").build();
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.OK).message("The user has been created").build();

        ResponseEntity response = target.createUser(user);

        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void create_user_email_constraint_exception() throws UserAlreadyRegisteredException {
        User user = User.builder().first_name("Mauricio").last_name("Figueroa").email("figueroa.a.mj@gmail.com").build();
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.CONFLICT).message("The email is already registered!, please insert a new mail").build();

        doThrow(new UserAlreadyRegisteredException("The email is already registered!, please insert a new mail")).when(userService).createUser(user);
        ResponseEntity response = target.createUser(user);

        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void create_user_server_error() throws UserAlreadyRegisteredException {
        User user = User.builder().first_name("Mauricio").last_name("Figueroa").email("figueroa.a.mj@gmail.com").build();
        DefaultResponseDTO expected = DefaultResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Conflict on the server, the best engineers are running to fix this").build();

        doThrow(new RuntimeException()).when(userService).createUser(user);
        ResponseEntity response = target.createUser(user);

        DefaultResponseDTO actual = (DefaultResponseDTO) response.getBody();
        Assert.assertEquals(actual, expected);
    }

}