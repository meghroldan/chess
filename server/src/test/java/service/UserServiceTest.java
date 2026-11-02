package service;

import dataaccess.DataAccessException;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;
import service.UserService;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void registerCorrectly() throws Exception {

        RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

        UserService userService = new UserService();
        RegisterResult result;
        result = userService.register(request);

        assertEquals("megan", result.username());
        assertNotNull(result.authToken());
    }

    @Test
    public void userTaken() throws Exception {

        RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

        UserService userService = new UserService();
        RegisterResult result;
        result = userService.register(request);
        //result = userService.register(request);


        DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.register(request));

        assertEquals("Error: already taken", exception.getMessage());
    }

    @Test
    public void registerNoName() throws Exception {

        RegisterRequest request = new RegisterRequest(null, "1234", "megan@gmail.com");

        UserService userService = new UserService();

        DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.register(request));

        assertEquals("Error: bad request", exception.getMessage());
        //assertNotNull(result.authToken());
    }

    @Test
    public void registerNoPassword() throws Exception {

        RegisterRequest request =
                new RegisterRequest("megan", null, "megan@gmail.com");

        UserService userService = new UserService();

        DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.register(request));

        assertEquals("Error: bad request", exception.getMessage());
        //assertNotNull(result.authToken());
    }

    @Test
    public void registerNoEmail() throws Exception {

        RegisterRequest request =
                new RegisterRequest("megan", "1234", null);

        UserService userService = new UserService();

        DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.register(request));

        assertEquals("Error: bad request", exception.getMessage());
        //assertNotNull(result.authToken());
    }

    @Test
    public void loginCorrectly() throws Exception {

        LoginRequest loginR = new LoginRequest("megan", "1234");

        UserService userService = new UserService();

        RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

        userService.register(request);
        LoginResult result = userService.login(loginR);


        assertEquals("megan", result.username());
        //assertNotNull(result.authToken());
    }

    @Test
    public void loginNoName() throws Exception {

        LoginRequest loginR = new LoginRequest(null, "1234");

        UserService userService = new UserService();

        RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

        userService.register(request);

        DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.login(loginR));

        assertEquals("Error: bad request", exception.getMessage());
        //assertNotNull(result.authToken());
    }

    @Test
    public void loginNoPassword() throws Exception {

        LoginRequest loginR = new LoginRequest("megan", null);

        UserService userService = new UserService();

        RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

        userService.register(request);

        DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.login(loginR));

        assertEquals("Error: bad request", exception.getMessage());
        //assertNotNull(result.authToken());
    }

    @Test
    public void loginWrongPassword() throws Exception {

        LoginRequest loginR = new LoginRequest("megan", "1324");

        UserService userService = new UserService();

        RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

        userService.register(request);

        DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.login(loginR));

        assertEquals("Error: unauthorized", exception.getMessage());
        //assertNotNull(result.authToken());
    }
}
