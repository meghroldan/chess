package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;

/**
 * Handles all logic and requests for clearing the data stored
 */
public class ClearService {

    private final UserDAO userDAO;
    private final AuthDAO authDAO;
    private final GameDAO gameDAO;

    /**
     * Ensures same UserDAO, AuthDAO, and GameDAO are passed in to be cleared
     * @param userDAO1 userDAO
     * @param authDAO1 authDAO
     * @param gameDAO1 gameDAO
     */
    public ClearService(UserDAO userDAO1, AuthDAO authDAO1, GameDAO gameDAO1){
        userDAO = userDAO1;
        authDAO = authDAO1;
        gameDAO = gameDAO1;
    }

    /**
     * Clears data from memory
     * @throws DataAccessException if bad request
     */
    public void clearAll() throws DataAccessException {
        userDAO.clear();
        authDAO.clear();
        gameDAO.clear();

    }
}
