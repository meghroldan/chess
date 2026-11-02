package server.handlers;

import service.UserService;

public class UserHandler {

    private UserService userService;

    public UserHandler(UserService userService1){
        userService = userService1;
    }
}
