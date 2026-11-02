package service;

import dataaccess.DataAccessException;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.RegisterResult;
import service.UserService;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void registerCorrectly() throws Exception {

        RegisterRequest request =
                new RegisterRequest(
                        "megan",
                        "1234",
                        "megan@gmail.com");

        UserService userService = new UserService();
        RegisterResult result;
        result = userService.register(request);

        assertEquals("megan", result.username());
        assertNotNull(result.authToken());
    }

    @Test
    public void userTaken() throws Exception {

        RegisterRequest request =
                new RegisterRequest(
                        "megan",
                        "1234",
                        "megan@gmail.com");

        UserService userService = new UserService();
        RegisterResult result;
        result = userService.register(request);
        //result = userService.register(request);


        DataAccessException exception = assertThrows(
                DataAccessException.class,
                () -> userService.register(request)
        );

        assertEquals("Error: already taken", exception.getMessage());
    }

    @Test
    public void registerNoName() throws Exception {

        RegisterRequest request =
                new RegisterRequest(
                        null,
                        "1234",
                        "megan@gmail.com");

        UserService userService = new UserService();

        DataAccessException exception = assertThrows(
                DataAccessException.class,
                () -> userService.register(request)
        );

        assertEquals("Error: bad request", exception.getMessage());
        //assertNotNull(result.authToken());
    }

    @Test
    public void registerNoPassword() throws Exception {

        RegisterRequest request =
                new RegisterRequest(
                        "megan",
                        null,
                        "megan@gmail.com");

        UserService userService = new UserService();

        DataAccessException exception = assertThrows(
                DataAccessException.class,
                () -> userService.register(request)
        );

        assertEquals("Error: bad request", exception.getMessage());
        //assertNotNull(result.authToken());
    }

    @Test
    public void registerNoEmail() throws Exception {

        RegisterRequest request =
                new RegisterRequest(
                        "megan",
                        "1234",
                        null);

        UserService userService = new UserService();

        DataAccessException exception = assertThrows(
                DataAccessException.class,
                () -> userService.register(request)
        );

        assertEquals("Error: bad request", exception.getMessage());
        //assertNotNull(result.authToken());
    }
}
