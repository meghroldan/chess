package client;

import model.AuthData;
import request.RegisterRequest;
import result.GameCreationResult;
import result.GameListResult;
import result.LoginResult;

public class ServerFacade {

    private final String serverUrl;
    private GameListResult listGames;
    private LoginResult loginResult;
    private RegisterRequest registerRequest;
    private GameCreationResult gameCreationResult;

    public ServerFacade(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public ServerFacade(int port) {
        this.serverUrl = "http://localhost:" + port;
    }


    public RegisterRequest register(String username, String password, String email){
        return registerRequest;
    }

    public LoginResult login(String username, String password){
        return loginResult;
    }

    public void logout(String authToken){

    }

    public GameCreationResult createGame(String authToken, String gameName){
        return gameCreationResult;
    }

    public GameListResult listGames(String authToken){
        return listGames;
    }

    public void joinGame(String authToken, String color, int gameID){

    }
}
