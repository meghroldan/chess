package client.test;

import client.ServerFacade;
import dataaccess.DataAccessException;
import model.AuthData;
import org.junit.jupiter.api.*;
import request.RegisterRequest;
import result.RegisterResult;
import server.Server;
import service.ClearService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ServerFacadeTests {

    private static Server server;
    private static int port;
    private static ServerFacade facade;

    @BeforeAll
    public static void init() {
        server = new Server();
        port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
    }

    @BeforeEach
    void setup() throws Exception {
        facade = new ServerFacade(port);
        facade.clear();
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    void registerSuccess() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");


        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.authToken());
        Assertions.assertEquals("meg", result.username());
    }

    @Test
    void registerFail() throws Exception {

        facade.register("meg", "1234", "meg@gmail.com");
        DataAccessException exception = assertThrows(DataAccessException.class, () -> facade.register("meg", "1234", "meg@gmail.com"));
    }

    @Test
    void gameCreateSuccess() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");

        assertDoesNotThrow(() -> (facade.createGame(result.authToken(), "Meg's Game")));
    }

    @Test
    void gameCreateFail() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");
        facade.createGame(result.authToken(), "Meg's Game");

        DataAccessException exception = assertThrows(DataAccessException.class, () -> facade.createGame(result.authToken(), null));
    }

    @Test
    void logoutSuccess() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");
        facade.createGame(result.authToken(), "Meg's Game");

        assertDoesNotThrow(() -> facade.logout(result.authToken()));
    }

    @Test
    void logoutFail() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");
        facade.createGame(result.authToken(), "Meg's Game");

        DataAccessException exception = assertThrows(DataAccessException.class, () -> facade.logout("randomString"));
    }

    @Test
    void joinSuccess() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");
        facade.createGame(result.authToken(), "Meg's Game");

        assertDoesNotThrow(() -> facade.joinGame(result.authToken(), "WHITE", 1));
    }

    @Test
    void joinFail() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");
        facade.createGame(result.authToken(), "Meg's Game");

        DataAccessException exception = assertThrows(DataAccessException.class, () -> facade.joinGame(result.authToken(), "GREEN", 1));
    }

    @Test
    void listSuccess() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");
        facade.createGame(result.authToken(), "Meg's Game");

        assertDoesNotThrow(() -> facade.listGames(result.authToken()));
    }

    @Test
    void listFail() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");
        facade.createGame(result.authToken(), "Meg's Game");

        DataAccessException exception = assertThrows(DataAccessException.class, () -> facade.listGames("String"));
    }

    @Test
    void loginSuccess() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");

        assertDoesNotThrow(() -> facade.login("meg", "1234"));
    }

    @Test
    void loginFail() throws Exception {

        RegisterResult result = facade.register("meg", "1234", "meg@gmail.com");

        DataAccessException exception = assertThrows(DataAccessException.class, () -> facade.login("notmeg", "nope"));
    }

}
