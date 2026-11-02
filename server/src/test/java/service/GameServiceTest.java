package service;

import dataaccess.*;
import org.junit.jupiter.api.Test;
import request.GameCreationRequest;
import request.GameJoinRequest;
import request.RegisterRequest;
import result.GameCreationResult;
import result.RegisterResult;
import service.GameService;
import service.UserService;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    @Test
    public void createGameCorrectly() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest("game");
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");

        GameDAO newgame = new MemoryGameDAO();
        AuthDAO newauth = new MemoryAuthDAO();
        UserDAO newuser = new MemoryUserDAO();


        GameService gameService = new GameService(newauth, newgame);
        UserService userService = new UserService(newuser, newauth);

        RegisterResult result;
        result = userService.register(requestRegister);

        GameCreationResult resultGame;
        resultGame = gameService.createGame(result.authToken(), requestGame);

        assertEquals(1, resultGame.gameID());
        assertNotNull(result.authToken());
    }

    @Test
    public void createGameNullName() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest(null);
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");

        GameDAO newgame = new MemoryGameDAO();
        AuthDAO newauth = new MemoryAuthDAO();
        UserDAO newuser = new MemoryUserDAO();


        GameService gameService = new GameService(newauth, newgame);
        UserService userService = new UserService(newuser, newauth);

        RegisterResult result;
        result = userService.register(requestRegister);


        DataAccessException exception = assertThrows(DataAccessException.class, () -> gameService.createGame(result.authToken(), requestGame));

        assertEquals("Error: bad request", exception.getMessage());
    }

    @Test
    public void createGameNullAuthToken() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest(null);

        GameDAO newgame = new MemoryGameDAO();
        AuthDAO newauth = new MemoryAuthDAO();

        GameService gameService = new GameService(newauth, newgame);

        DataAccessException exception = assertThrows(DataAccessException.class, () -> gameService.createGame(null, requestGame));

        assertEquals("Error: unauthorized", exception.getMessage());
    }

    @Test
    public void joinGameCorrectlyWhite() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest("game");
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");


        GameDAO newgame = new MemoryGameDAO();
        AuthDAO newauth = new MemoryAuthDAO();
        UserDAO newuser = new MemoryUserDAO();


        GameService gameService = new GameService(newauth, newgame);
        UserService userService = new UserService(newuser, newauth);

        RegisterResult result;
        result = userService.register(requestRegister);

        GameCreationResult resultGame;
        resultGame = gameService.createGame(result.authToken(), requestGame);

        GameJoinRequest requestJoin = new GameJoinRequest("WHITE", resultGame.gameID());
        assertDoesNotThrow(() -> gameService.joinGame(result.authToken(), requestJoin));
    }

    @Test
    public void joinGameCorrectlyBlack() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest("game");
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");


        GameDAO newgame = new MemoryGameDAO();
        AuthDAO newauth = new MemoryAuthDAO();
        UserDAO newuser = new MemoryUserDAO();


        GameService gameService = new GameService(newauth, newgame);
        UserService userService = new UserService(newuser, newauth);

        RegisterResult result;
        result = userService.register(requestRegister);

        GameCreationResult resultGame;
        resultGame = gameService.createGame(result.authToken(), requestGame);

        GameJoinRequest requestJoin = new GameJoinRequest("BLACK", resultGame.gameID());
        assertDoesNotThrow(() -> gameService.joinGame(result.authToken(), requestJoin));
    }

    @Test
    public void joinGameBadAuth() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest("game");
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");


        GameDAO newgame = new MemoryGameDAO();
        AuthDAO newauth = new MemoryAuthDAO();
        UserDAO newuser = new MemoryUserDAO();


        GameService gameService = new GameService(newauth, newgame);
        UserService userService = new UserService(newuser, newauth);

        RegisterResult result;
        result = userService.register(requestRegister);

        GameCreationResult resultGame;
        resultGame = gameService.createGame(result.authToken(), requestGame);

        GameJoinRequest requestJoin = new GameJoinRequest(null, resultGame.gameID());
        DataAccessException exception = assertThrows(DataAccessException.class, () -> gameService.joinGame("40", requestJoin));

        assertEquals("Error: bad request", exception.getMessage());
    }

    @Test
    public void joinGameBadColor() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest("game");
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");


        GameDAO newgame = new MemoryGameDAO();
        AuthDAO newauth = new MemoryAuthDAO();
        UserDAO newuser = new MemoryUserDAO();


        GameService gameService = new GameService(newauth, newgame);
        UserService userService = new UserService(newuser, newauth);

        RegisterResult result;
        result = userService.register(requestRegister);

        GameCreationResult resultGame;
        resultGame = gameService.createGame(result.authToken(), requestGame);

        GameJoinRequest requestJoin = new GameJoinRequest(null, resultGame.gameID());
        DataAccessException exception = assertThrows(DataAccessException.class, () -> gameService.joinGame(result.authToken(), requestJoin));

        assertEquals("Error: bad request", exception.getMessage());
    }

    @Test
    public void listGames() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest("game");
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");


        GameDAO newgame = new MemoryGameDAO();
        AuthDAO newauth = new MemoryAuthDAO();
        UserDAO newuser = new MemoryUserDAO();


        GameService gameService = new GameService(newauth, newgame);
        UserService userService = new UserService(newuser, newauth);

        RegisterResult result;
        result = userService.register(requestRegister);

        GameCreationResult resultGame;
        resultGame = gameService.createGame(result.authToken(), requestGame);

        GameJoinRequest requestJoin = new GameJoinRequest("BLACK", resultGame.gameID());
        assertDoesNotThrow(() -> gameService.getGamesList(result.authToken()));
    }

}
