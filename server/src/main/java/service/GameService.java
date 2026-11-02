package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.GameData;
import model.UserData;
import request.GameCreationRequest;
import request.GameJoinRequest;
import result.GameCreationResult;
import result.GameListResult;

import java.util.ArrayList;
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
        if(gameCreationRequest.gameName() == null || gameCreationRequest.gameName().equals("")){
            throw new DataAccessException("Error: bad request");
        }

        GameCreationResult result = gameDAO.createGame(gameCreationRequest);

        return result;
    }

    public void joinGame(String token, GameJoinRequest request) throws DataAccessException {

        String playColor = request.playerColor();
        if(playColor == null){
            throw new DataAccessException("Error: bad request");
        }
        if(!(playColor.equals("WHITE")) && !(playColor.equals("BLACK"))){
            throw new DataAccessException("Error: bad request");
        }
        Integer gameID = request.gameID();
        //TODO add tests for no token, playColor, gameID, WHITE and BLACK taken, WHITE and BLACK free
        System.out.println("Received token = " + token);
        AuthData tokenData = authDAO.getAuth(token);

        //debugging
        System.out.println("Received token = " + token);

        //AuthData tokenData = authDAO.getAuth(token);

        System.out.println("AuthData = " + tokenData);


        if(tokenData == null){
            throw new DataAccessException("Error: unauthorized");
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

    public GameListResult getGamesList(String token)  throws DataAccessException {

        //TODO write successful and not successful tests
        AuthData tokenData = authDAO.getAuth(token);
        if(tokenData == null){
            throw new DataAccessException("Error: unauthorized");
        }
        //if(gameDAO.listGames().isEmpty()){
        //    return new HashMap<Integer, GameData>();
        //}

        HashMap<Integer, GameData> list = gameDAO.listGames();

        GameListResult listResult = new GameListResult(new ArrayList<>(list.values()));

        return listResult;
    }


}
