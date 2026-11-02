package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import result.LoginResult;
import result.RegisterResult;

public class ClearService {

    private UserDAO userDAO;
    private AuthDAO authDAO;
    private GameDAO gameDAO;
    private String user;


    public ClearService(UserDAO userDAO1, AuthDAO authDAO1, GameDAO gameDAO1){
        userDAO = userDAO1;
        authDAO = authDAO1;
        gameDAO = gameDAO1;
    }


    //TODO write tests
    public void clearAll() throws DataAccessException {
        userDAO.clear();
        authDAO.clear();
        gameDAO.clear();

    }
}
