package server.handlers;

import service.GameService;

public class GameHandler {

    private GameService gameService;

    public GameHandler(GameService gameService1){
        gameService = gameService1;
    }
}
