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

public class UserService {
    private LoginResult loginR;
    private UserDAO userDAO;
    private AuthDAO authDAO;
    private String user;
    private RegisterResult registerR;

        public UserService(){
            userDAO = new MemoryUserDAO();
            authDAO = new MemoryAuthDAO();
        }


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
            //create authtoken and data
            String token = UUID.randomUUID().toString();
            //if(token == null){
            //    throw new DataAccessException("Error: bad request");
            //}
            AuthData authData = new AuthData(token, user);
            authDAO.createAuth(authData);

            //create user
            UserData userData = new UserData(user, registerRequest.password(), registerRequest.email());
            userDAO.createUser(userData);
            registerR = new RegisterResult(user, token);
            return registerR;
        }


        public LoginResult login(LoginRequest loginRequest) throws DataAccessException  {
            user = loginRequest.username();
            if(user == null){
                throw new DataAccessException("Error: bad request");
            }
            //if(loginRequest.authToken() == null){
            //    throw new DataAccessException("Error: bad request");
            //}
            userDAO.getUser(user);
            String token = UUID.randomUUID().toString();
            AuthData authData = new AuthData(user, token);
            authDAO.createAuth(authData);

            loginR = new LoginResult(user, token);
            return loginR;
        }


        public void logout(LogoutRequest logoutRequest) throws DataAccessException  {
            return;
        }

        /*
        var serializer = new Gson();

var game = new ChessGame();

// serialize to JSON
var json = serializer.toJson(game);

// deserialize back to ChessGame
game = serializer.fromJson(json, ChessGame.class);
         */

}
