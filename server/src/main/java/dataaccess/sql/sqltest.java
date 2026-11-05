package dataaccess.sql;

import dataaccess.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.*;
import result.GameCreationResult;
import result.LoginResult;
import result.RegisterResult;
import service.GameService;
import service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class sqltest {
    private UserDAO newuser = new SQLUserDAO();
    private AuthDAO newauth = new SQLAuthDAO();
    private GameDAO newgame = new SQLGameDAO();

    @BeforeEach
    public void clearDatabase() throws DataAccessException {

        newuser.clear();
        newauth.clear();
        newgame.clear();
    }

        @Test
        public void registerCorrectly() throws Exception {

            RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);
            RegisterResult result;
            result = userService.register(request);

            assertEquals("megan", result.username());
            assertNotNull(result.authToken());
        }

        @Test
        public void userTaken() throws Exception {

            RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);
            userService.register(request);


            DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.register(request));

            assertEquals("Error: already taken", exception.getMessage());
        }

        @Test
        public void registerNoName() throws Exception {

            RegisterRequest request = new RegisterRequest(null, "1234", "megan@gmail.com");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.register(request));

            assertEquals("Error: bad request", exception.getMessage());
            //assertNotNull(result.authToken());
        }

        @Test
        public void registerNoPassword() throws Exception {

            RegisterRequest request =
                    new RegisterRequest("megan", null, "megan@gmail.com");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.register(request));

            assertEquals("Error: bad request", exception.getMessage());
            //assertNotNull(result.authToken());
        }

        @Test
        public void registerNoEmail() throws Exception {

            RegisterRequest request =
                    new RegisterRequest("megan", "1234", null);

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.register(request));

            assertEquals("Error: bad request", exception.getMessage());
            //assertNotNull(result.authToken());
        }

        @Test
        public void loginCorrectly() throws Exception {

            LoginRequest loginR = new LoginRequest("megan", "1234");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

            userService.register(request);
            LoginResult result = userService.login(loginR);


            assertEquals("megan", result.username());
            //assertNotNull(result.authToken());
        }

        @Test
        public void loginNoName() throws Exception {

            LoginRequest loginR = new LoginRequest(null, "1234");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

            userService.register(request);

            DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.login(loginR));

            assertEquals("Error: bad request", exception.getMessage());
            //assertNotNull(result.authToken());
        }

        @Test
        public void loginNoPassword() throws Exception {

            LoginRequest loginR = new LoginRequest("megan", null);

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

            userService.register(request);

            DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.login(loginR));

            assertEquals("Error: bad request", exception.getMessage());
            //assertNotNull(result.authToken());
        }

        @Test
        public void loginWrongPassword() throws Exception {

            LoginRequest loginR = new LoginRequest("megan", "1324");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

            userService.register(request);

            DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.login(loginR));

            assertEquals("Error: unauthorized", exception.getMessage());
            //assertNotNull(result.authToken());
        }

        @Test
        public void logoutCorrectly() throws Exception {

            LoginRequest loginR = new LoginRequest("megan", "1234");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

            userService.register(request);
            LoginResult result = userService.login(loginR);

            LogoutRequest logoutRequest = new LogoutRequest(result.authToken());
            userService.logout(logoutRequest);


            DataAccessException exception = assertThrows(DataAccessException.class, () -> newauth.getAuth(result.authToken()));

            assertEquals("Error: unauthorized", exception.getMessage());
        }

        @Test
        public void logoutWrongAuthToken() throws Exception {

            LoginRequest loginR = new LoginRequest("megan", "1234");

            UserDAO newuser = new SQLUserDAO();
            AuthDAO newauth = new SQLAuthDAO();

            UserService userService = new UserService(newuser, newauth);

            RegisterRequest request = new RegisterRequest("megan", "1234", "megan@gmail.com");

            userService.register(request);
            LoginResult result = userService.login(loginR);

            LogoutRequest logoutRequest = new LogoutRequest("9132587");


            DataAccessException exception = assertThrows(DataAccessException.class, () -> userService.logout(logoutRequest));

            assertEquals("Error: unauthorized", exception.getMessage());
        }

    @Test
    public void createGameCorrectly() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest("game");
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");

        GameDAO newgame = new SQLGameDAO();
        AuthDAO newauth = new SQLAuthDAO();
        UserDAO newuser = new SQLUserDAO();


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

        GameDAO newgame = new SQLGameDAO();
        AuthDAO newauth = new SQLAuthDAO();
        UserDAO newuser = new SQLUserDAO();


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

        GameDAO newgame = new SQLGameDAO();
        AuthDAO newauth = new SQLAuthDAO();

        GameService gameService = new GameService(newauth, newgame);

        DataAccessException exception = assertThrows(DataAccessException.class, () -> gameService.createGame(null, requestGame));

        assertEquals("Error: unauthorized", exception.getMessage());
    }

    @Test
    public void joinGameCorrectlyWhite() throws Exception {

        GameCreationRequest requestGame = new GameCreationRequest("game");
        RegisterRequest requestRegister = new RegisterRequest("megan", "1234", "megan@gmail.com");


        GameDAO newgame = new SQLGameDAO();
        AuthDAO newauth = new SQLAuthDAO();
        UserDAO newuser = new SQLUserDAO();


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


        GameDAO newgame = new SQLGameDAO();
        AuthDAO newauth = new SQLAuthDAO();
        UserDAO newuser = new SQLUserDAO();


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


        GameDAO newgame = new SQLGameDAO();
        AuthDAO newauth = new SQLAuthDAO();
        UserDAO newuser = new SQLUserDAO();


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


        GameDAO newgame = new SQLGameDAO();
        AuthDAO newauth = new SQLAuthDAO();
        UserDAO newuser = new SQLUserDAO();


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


        GameDAO newgame = new SQLGameDAO();
        AuthDAO newauth = new SQLAuthDAO();
        UserDAO newuser = new SQLUserDAO();


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
