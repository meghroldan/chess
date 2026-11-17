package client;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.AuthData;
import request.GameCreationRequest;
import request.GameJoinRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.GameCreationResult;
import result.GameListResult;
import result.LoginResult;
import result.RegisterResult;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerFacade {

    private final String serverUrl;
    private GameListResult listGames;
    private LoginResult loginResult;
    private RegisterResult registerResult;
    private GameCreationResult gameCreationResult;

    public ServerFacade(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public ServerFacade(int port) {
        this.serverUrl = "http://localhost:" + port;
    }

    public void clear() throws DataAccessException {
        makeResult("DELETE", "/db", null, null, null);
    }


    public RegisterResult register(String username, String password, String email) throws DataAccessException {

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        registerResult = makeResult("POST", "/user", null, registerRequest, RegisterResult.class);
        return registerResult;
    }

    public LoginResult login(String username, String password) throws DataAccessException {

        LoginRequest loginRequest = new LoginRequest(username, password);
        loginResult = makeResult("POST", "/session", null, loginRequest, LoginResult.class);
        return loginResult;
    }

    public void logout(String authToken) throws DataAccessException{
        makeResult("DELETE", "/session", authToken, null, null);
    }

    public GameCreationResult createGame(String authToken, String gameName) throws DataAccessException {

        GameCreationRequest gameCreationRequest = new GameCreationRequest(gameName);
        gameCreationResult = makeResult("POST", "/game", authToken, gameCreationRequest, GameCreationResult.class);
        return gameCreationResult;
    }

    public GameListResult listGames(String authToken) throws DataAccessException {
        return makeResult("GET", "/game", authToken, null, GameListResult.class);
    }

    public void joinGame(String authToken, String color, int gameID) throws DataAccessException {
        GameJoinRequest gameJoinRequest = new GameJoinRequest(color, gameID);
        makeResult("PUT", "/game", authToken, gameJoinRequest, null);
    }

    private <T> T makeResult(String method, String path, String authToken, Object requestBody, Class<T> responseClass) throws DataAccessException {

        try {
            System.out.println(serverUrl + path);
            URL url = new URL(serverUrl + path);

            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            // Auth
            if (authToken != null) {
                http.setRequestProperty("Authorization", authToken);
            }

            if (requestBody != null) {
                http.addRequestProperty(
                        "Content-Type",
                        "application/json");

                try (OutputStream output = http.getOutputStream()) {
                    String json = new Gson().toJson(requestBody);
                    output.write(json.getBytes());
                }
            }

            http.connect();
            if (http.getResponseCode() >= 400) {

                //System.out.println(http.getResponseCode());
                throw new DataAccessException("Request failed");
            }

            if (responseClass == null) {
                return null;
            }
            try (InputStreamReader reader = new InputStreamReader(http.getInputStream())) {

                return new Gson().fromJson(reader, responseClass);
            }

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }

    }
}
