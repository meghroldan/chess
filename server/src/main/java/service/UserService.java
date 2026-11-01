package service;

import request.LoginRequest;
import request.LogoutRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;

public class UserService {
        private RegisterResult registerR;

        private LoginResult loginR;

        public RegisterResult register(RegisterRequest registerRequest) {
            return registerR;
        }
        public LoginResult login(LoginRequest loginRequest) {
            return loginR;
        }
        public void logout(LogoutRequest logoutRequest) {
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
