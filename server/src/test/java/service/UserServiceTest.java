package java.service;

import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.RegisterResult;
import service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
