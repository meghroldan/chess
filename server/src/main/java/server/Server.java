package server;

import dataaccess.*;
import io.javalin.Javalin;
import server.handlers.ClearHandler;
import server.handlers.GameHandler;
import server.handlers.UserHandler;
import service.ClearService;
import service.GameService;
import service.UserService;

import io.javalin.http.Handler;

public class Server {

    private final Javalin javalin;
    private final UserDAO userDAO = new MemoryUserDAO();
    private final AuthDAO authDAO = new MemoryAuthDAO();
    private final GameDAO gameDAO = new MemoryGameDAO();

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        //Create Service
        UserService userService = new UserService(userDAO, authDAO);
        ClearService clearService = new ClearService(userDAO, authDAO, gameDAO);
        GameService gameService = new GameService(authDAO, gameDAO);

        //Add Handlers
        UserHandler userHandler = new UserHandler(userService);
        ClearHandler clearHandler = new ClearHandler(clearService);
        GameHandler gameHandler = new GameHandler(gameService);

        // Register your endpoints and exception handlers here.
        javalin.post("/user", userHandler.register);
        javalin.post("/session", userHandler.login);
        javalin.delete("/session", userHandler.logout);
        javalin.delete("/db", clearHandler.clear);
        javalin.post("/game", gameHandler.createGame);
        javalin.put("/game", gameHandler.joinGame);

        //javalin.addEndpoint(register);

    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
