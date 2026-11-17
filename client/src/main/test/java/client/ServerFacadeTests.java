package client.test;

import client.ServerFacade;
import model.AuthData;
import org.junit.jupiter.api.*;
import request.RegisterRequest;
import result.RegisterResult;
import server.Server;
import service.ClearService;


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
        System.out.println("Database cleared");
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    @DisplayName("Register Success")
    void registerSuccess() throws Exception {

        RegisterResult result =
                facade.register("meg", "1234", "meg@gmail.com");


        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.authToken());
        Assertions.assertEquals("meg", result.username());
    }

}
