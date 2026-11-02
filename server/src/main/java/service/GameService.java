package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.GameData;
import model.UserData;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.util.HashMap;

public class GameService {

    private AuthDAO authDAO;
    private GameDAO gameDAO;
    private String user;


    public GameService(AuthDAO authDAO1, GameDAO gameDAO1){
        authDAO = authDAO1;
        gameDAO = gameDAO1;
    }

    public GameCreationResult createGame(String token, GameCreationRequest gameCreationRequest) throws DataAccessException {

        //TODO working game, token null, and name null tests
        AuthData tokenData = authDAO.getAuth(token);
        if(tokenData == null){
            throw new DataAccessException("Error: unauthorized");
        }
        if(gameCreationRequest.gameName() == null){
            throw new DataAccessException("Error: bad request");
        }

        GameCreationResult result = gameDAO.createGame(gameCreationRequest);

        return result;
    }

    public void joinGame(String token, String playColor, Integer gameID) throws DataAccessException {

        //TODO add tests for no token, playColor, gameID, WHITE and BLACK taken, WHITE and BLACK free
        AuthData tokenData = authDAO.getAuth(token);
        if(tokenData == null){
            throw new DataAccessException("Error: unauthorized");
        }
        if(playColor == null){
            throw new DataAccessException("Error: bad request");
        }
        if(gameID == null){
            throw new DataAccessException("Error: bad request");
        }

        GameData gameData = checkColor(gameID);  //maybe get rid of helper method


        if(playColor.equals("WHITE") && gameData.whiteUsername() == null){
            gameDAO.updateGame(playColor, gameID, tokenData.username());
        }
        else if(playColor.equals("BLACK") && gameData.blackUsername() == null){
            gameDAO.updateGame(playColor, gameID, tokenData.username());
        }
        else{
            throw new DataAccessException("Error: already taken");
        }

    }

    public GameData checkColor(Integer gameID) throws DataAccessException {
            return gameDAO.getGame(gameID);
    }

    public HashMap<Integer, GameData> getGamesList(String token)  throws DataAccessException {

        //TODO write successful and not successful tests
        AuthData tokenData = authDAO.getAuth(token);
        if(tokenData == null){
            throw new DataAccessException("Error: unauthorized");
        }

        return gameDAO.listGames();
    }


}
