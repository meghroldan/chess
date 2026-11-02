package service;

import dataaccess.*;
import model.AuthData;
import model.UserData;
import request.LoginRequest;
import request.LogoutRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;

import java.util.UUID;

/**
 * A service class that logs a user in, out, and registers users.
 * Handles anything to do with the user themselves
 */
public class UserService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;
    private String user;


    /**
     * Creating a service including the userDAO and authDAO that was created in the server
     * @param userDAO1
     * @param authDAO1
     */
    public UserService(UserDAO userDAO1, AuthDAO authDAO1){
        userDAO = userDAO1;
        authDAO = authDAO1;
    }

    /**
     * Takes in username, password, and email. Returns username and authToken. Allows no repeat usernames
     * @param registerRequest including username, password, and email
     * @return RegisterResult, which includes a username and authToken
     * @throws DataAccessException
     */
    public RegisterResult register(RegisterRequest registerRequest) throws DataAccessException {
        user = registerRequest.username();
        if(user == null){
            throw new DataAccessException("Error: bad request");
        }
        if(registerRequest.password() == null){
            throw new DataAccessException("Error: bad request");
        }
        if(registerRequest.email() == null){
            throw new DataAccessException("Error: bad request");
        }
        if(userDAO.getUserRegister(user) != null){
            throw new DataAccessException("Error: already taken");
        }
        //create authtoken and data
        String token = UUID.randomUUID().toString();

        AuthData authData = new AuthData(token, user);
        authDAO.createAuth(authData);

        //create user
        UserData userData = new UserData(user, registerRequest.password(), registerRequest.email());
        userDAO.createUser(userData);
        RegisterResult registerR = new RegisterResult(user, token);
        return registerR;
    }

    /**
     *Log a user in with username and password. Returns and authToken
     * @param loginRequest includes a username and password
     * @return LoginResult which includes an authToken
     * @throws DataAccessException
     */
    public LoginResult login(LoginRequest loginRequest) throws DataAccessException  {
        user = loginRequest.username();
        if(user == null){
            throw new DataAccessException("Error: bad request");
        }

        if(loginRequest.password() == null){
            throw new DataAccessException("Error: bad request");
        }

        UserData userData = userDAO.getUser(user);

        if(!(userData.password().equals(loginRequest.password()))){
            throw new DataAccessException("Error: unauthorized");
        }

        String token = UUID.randomUUID().toString();
        AuthData authData = new AuthData(token, user);
        authDAO.createAuth(authData);

        LoginResult loginR = new LoginResult(user, token);
        return loginR;
    }

    /**
     * Logs a single authToken from a user out
     * @param logoutRequest Takes in a LogoutRequest, which includes an authToken
     * @throws DataAccessException if the authToken is null
     */
    public void logout(LogoutRequest logoutRequest) throws DataAccessException  {
            String token = logoutRequest.authToken();
            if(token == null){
                throw new DataAccessException("Error: unauthorized");
            }

            authDAO.deleteAuth(token);
        }

}
