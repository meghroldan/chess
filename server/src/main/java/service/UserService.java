package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import request.LoginRequest;
import request.LogoutRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;

import java.util.UUID;

public class UserService {
        private RegisterResult registerR;
        private LoginResult loginR;
        private UserDAO userDAO;
        private AuthDAO authDAO;
        private String user;

        public RegisterResult register(RegisterRequest registerRequest) throws DataAccessException {
            if(registerRequest.username() == null){
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
            if(token == null){
                throw new DataAccessException("Error: bad request");
            }
            AuthData authData = new AuthData(token, registerRequest.username());
            authDAO.createAuth(authData);

            //create user
            UserData userData = new UserData(registerRequest.username(), registerRequest.password(), registerRequest.email());
            userDAO.createUser(userData);
            registerR = new RegisterResult(registerRequest.username(), token);
            return registerR;
        }
        public LoginResult login(LoginRequest loginRequest) throws DataAccessException  {
            if(loginRequest.username() == null){
                throw new DataAccessException("Error: bad request");
            }
            //if(loginRequest.authToken() == null){
            //    throw new DataAccessException("Error: bad request");
            //}
            userDAO.getUser(loginRequest.username());
            String token = UUID.randomUUID().toString();
            AuthData authData = new AuthData(loginRequest.username(), token);
            authDAO.createAuth(authData);

            loginR = new LoginResult(loginRequest.username(), token);
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
