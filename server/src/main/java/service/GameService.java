package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.AuthData;
import model.GameData;
import request.GameCreationRequest;
import request.GameJoinRequest;
import result.GameCreationResult;
import result.GameListResult;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Includes anything to do with the games themselves. Can list, join, or create games
 */
public class GameService {

    private final AuthDAO authDAO;
    private final GameDAO gameDAO;

    /**
     *  Creating a service including the gameDAO and authDAO that was created in the server
     * @param authDAO1 all authTokens and models handled here
     * @param gameDAO1 all gameData handled here
     */
    public GameService(AuthDAO authDAO1, GameDAO gameDAO1){
        authDAO = authDAO1;
        gameDAO = gameDAO1;
    }

    /**
     * Creates game with gameName. Takes in authToken
     * @param token authToken
     * @param gameCreationRequest gameName
     * @return GameCreation successes and errors
     * @throws DataAccessException if game cannot be created or bad request
     */
    public GameCreationResult createGame(String token, GameCreationRequest gameCreationRequest) throws DataAccessException {

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

    /**
     * Takes in an authToken, color and gameID to see if a player can join as that color
     * @param token authToken
     * @param request color and gameID
     * @throws DataAccessException if player cannot join or bad request
     */
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

        System.out.println("AuthData = " + tokenData);


        if(tokenData == null){
            throw new DataAccessException("Error: unauthorized");
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

    /**
     * Helper method to see what color it is
     * @param gameID gameID
     * @return GameData
     * @throws DataAccessException
     */
    public GameData checkColor(Integer gameID) throws DataAccessException {
            return gameDAO.getGame(gameID);
    }


    /**
     * Returns list of games
     * @param token authToken
     * @return GameList
     * @throws DataAccessException if token is not valid
     */
    public GameListResult getGamesList(String token)  throws DataAccessException {

        //TODO write successful and not successful tests
        AuthData tokenData = authDAO.getAuth(token);
        if(tokenData == null){
            throw new DataAccessException("Error: unauthorized");
        }

        HashMap<Integer, GameData> list = gameDAO.listGames();

        GameListResult listResult = new GameListResult(new ArrayList<>(list.values()));

        return listResult;
    }


}
