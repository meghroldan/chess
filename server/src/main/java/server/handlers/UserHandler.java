package server.handlers;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import request.RegisterRequest;
import result.RegisterResult;
import service.UserService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class UserHandler{

    private UserService userService;

    public UserHandler(UserService userService1){

        userService = userService1;
    }

    public Handler register = ctx -> {

        Gson gson = new Gson();

        String body = ctx.body();

        RegisterRequest request = gson.fromJson(body, RegisterRequest.class);

        RegisterResult result = userService.register(request);

        var json = gson.toJson(result);

        ctx.result(json);


    };

}
