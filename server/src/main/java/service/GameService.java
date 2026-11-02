package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import request.GameCreationRequest;
import result.GameCreationResult;

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


}
