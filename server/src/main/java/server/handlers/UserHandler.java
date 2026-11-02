package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import org.jetbrains.annotations.NotNull;
import request.LoginRequest;
import request.LogoutRequest;
import request.RegisterRequest;
import result.LoginResult;
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
        try {
            Gson gson = new Gson();
            String body = ctx.body();

            RegisterRequest request = gson.fromJson(body, RegisterRequest.class);
            RegisterResult result = userService.register(request);

            var json = gson.toJson(result);
            ctx.result(json);

        }catch(DataAccessException exception) {
            if (exception.getMessage().equals("Error: unauthorized")) {
                ctx.status(401);
                ctx.result("{\"message\":\"Error: unauthorized\"}"); // TODO maybe json this ctx.result(gson.toJson(exception.getMessage())
            }
            if (exception.getMessage().equals("Error: bad request")){
                ctx.status(400);
                ctx.result("{\"message\":\"Error: bad request\"}"); //maybe json this
            }
            if (exception.getMessage().equals("Error: already taken")){
                ctx.status(403);
                ctx.result("{\"message\":\"Error: already taken\"}"); //maybe json this
            }
        }

    };



    public Handler login = ctx -> {
        try {
            Gson gson = new Gson();
            String body = ctx.body();

            LoginRequest request = gson.fromJson(body, LoginRequest.class);
            LoginResult result = userService.login(request);

            var json = gson.toJson(result);
            ctx.status(200);
            ctx.result(json);
        }
        catch(DataAccessException exception){
            if (exception.getMessage().equals("Error: unauthorized")){
                ctx.status(401);
                ctx.result("{\"message\":\"Error: unauthorized\"}"); //maybe json this
            }
            if (exception.getMessage().equals("Error: bad request")){
                ctx.status(400);
                ctx.result("{\"message\":\"Error: bad request\"}"); //maybe json this
            }
            if (exception.getMessage().equals("Error: already taken")){
                ctx.status(403);
                ctx.result("{\"message\":\"Error: already taken\"}"); //maybe json this
            }
        }
    };


    public Handler logout = ctx -> {
        try {
            String authToken = ctx.header("Authorization");
            Gson gson = new Gson();
            String body = ctx.body();

            LogoutRequest request = new LogoutRequest(authToken);
            userService.logout(request);

            //var json = gson.toJson(result);
            ctx.status(200);
            //ctx.result(json);
        }
        catch(DataAccessException exception){
            if (exception.getMessage().equals("Error: unauthorized")){
                ctx.status(401);
                ctx.result("{\"message\":\"Error: unauthorized\"}"); //maybe json this
            }
            if (exception.getMessage().equals("Error: bad request")){
                ctx.status(400);
                ctx.result("{\"message\":\"Error: bad request\"}"); //maybe json this
            }
            if (exception.getMessage().equals("Error: already taken")){
                ctx.status(403);
                ctx.result("{\"message\":\"Error: already taken\"}"); //maybe json this
            }
        }

    };

}
