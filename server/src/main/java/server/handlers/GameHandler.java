package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import io.javalin.http.Handler;
import request.GameCreationRequest;
import request.LogoutRequest;
import request.RegisterRequest;
import result.GameCreationResult;
import service.GameService;

public class GameHandler {

    private GameService gameService;

    public GameHandler(GameService gameService1){
        gameService = gameService1;
    }

    public Handler createGame = ctx -> {
        try {
            String authToken = ctx.header("Authorization");
            Gson gson = new Gson();
            String body = ctx.body();

            GameCreationRequest request = gson.fromJson(body, GameCreationRequest.class);
            GameCreationResult result = gameService.createGame(authToken, request);

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
}
