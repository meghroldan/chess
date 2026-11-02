package service;

import dataaccess.*;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.LogoutRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;
import service.ClearService;
import service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClearServiceTest {

    @Test
    public void clearCorrectly() throws Exception {

        LoginRequest loginR = new LoginRequest("megan", "1234");

        UserDAO newuser = new MemoryUserDAO();
        AuthDAO newauth = new MemoryAuthDAO();
        GameDAO newgame = new MemoryGameDAO();

        UserService userService = new UserService(newuser, newauth);
        ClearService clearService = new ClearService(newuser, newauth, newgame);

        RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

        RegisterResult result = userService.register(request);
        LogoutRequest logoutRequest = new LogoutRequest(result.authToken());
        clearService.clearAll();

        DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.logout(logoutRequest));

        assertEquals("Error: unauthorized", exception.getMessage());
    }
}
