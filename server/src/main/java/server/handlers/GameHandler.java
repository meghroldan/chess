package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import io.javalin.http.Handler;
import request.GameCreationRequest;
import request.GameJoinRequest;
import result.GameCreationResult;
import result.GameListResult;
import service.GameService;

/**
 * Handles all game requests from user to service, translating from JSON to Objects and back
 */
public class GameHandler {

    private GameService gameService;

    /**
     * Ensures one GameService is in use at a time
     * @param gameService1
     */
    public GameHandler(GameService gameService1){
        gameService = gameService1;
    }

    /**
     * Creates game requests to send to service and reports back success and errors
     */
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
            else if (exception.getMessage().equals("Error: bad request")){
                ctx.status(400);
                ctx.result("{\"message\":\"Error: bad request\"}"); //maybe json this
            }
            else if (exception.getMessage().equals("Error: already taken")){
                ctx.status(403);
                ctx.result("{\"message\":\"Error: already taken\"}"); //maybe json this
            }
            else{
                ctx.status(500);
                ctx.result("{\"message\":\"Error: server error\"}"); //maybe json this
            }
        }

    };

    /**
     * Creates join game requests to send to service and reports back success and errors
     */
    public Handler joinGame = ctx -> {
        try {
            String authToken = ctx.header("Authorization");
            Gson gson = new Gson();
            String body = ctx.body();

            GameJoinRequest request = gson.fromJson(body, GameJoinRequest.class);
            gameService.joinGame(authToken, request);

            ctx.status(200);
        }
        catch(DataAccessException exception){
            if (exception.getMessage().equals("Error: unauthorized")){
                ctx.status(401);
                ctx.result("{\"message\":\"Error: unauthorized\"}"); //maybe json this
            }
            else if (exception.getMessage().equals("Error: bad request")){
                ctx.status(400);
                ctx.result("{\"message\":\"Error: bad request\"}"); //maybe json this
            }
            else if (exception.getMessage().equals("Error: already taken")){
                ctx.status(403);
                ctx.result("{\"message\":\"Error: already taken\"}"); //maybe json this
            }
            else{
                ctx.status(500);
                ctx.result("{\"message\":\"Error: server error\"}"); //maybe json this
            }
        }

    };


    /**
     * Creates list game requests to send to service and reports back success and errors
     */
    public Handler listGames = ctx -> {
        try {
            String authToken = ctx.header("Authorization");
            Gson gson = new Gson();

            GameListResult list = gameService.getGamesList(authToken);

            var json = gson.toJson(list);
            ctx.status(200);
            ctx.result(json);
        }
        catch(DataAccessException exception){
            if (exception.getMessage().equals("Error: unauthorized")){
                ctx.status(401);
                ctx.result("{\"message\":\"Error: unauthorized\"}"); //maybe json this
            }
            else if (exception.getMessage().equals("Error: bad request")){
                ctx.status(400);
                ctx.result("{\"message\":\"Error: bad request\"}"); //maybe json this
            }
            else if (exception.getMessage().equals("Error: already taken")){
                ctx.status(403);
                ctx.result("{\"message\":\"Error: already taken\"}"); //maybe json this
            }
            else{
                ctx.status(500);
                ctx.result("{\"message\":\"Error: server error\"}"); //maybe json this
            }
        }

    };
}
