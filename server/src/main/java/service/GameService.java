package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.UserDAO;

public class GameService {

    private AuthDAO authDAO;
    private GameDAO gameDAO;
    private String user;


    public GameService(AuthDAO authDAO1, GameDAO gameDAO1){
        authDAO = authDAO1;
        gameDAO = gameDAO1;
    }
}
