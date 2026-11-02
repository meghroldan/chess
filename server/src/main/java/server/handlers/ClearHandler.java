package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import io.javalin.http.Handler;
import service.ClearService;

/**
 * Handles sending all clear requests from user to service, translating from JSON to Objects and back
 */
public class ClearHandler {
    private ClearService clearService;

    /**
     * Ensures there is one ClearService used at a time
     * @param clearService1
     */
    public ClearHandler(ClearService clearService1){
        clearService = clearService1;
    }

    /**
     * Takes in the ctx from user and turns into the clear request for the service
     * Reports back successes and errors
     */
    public Handler clear = ctx -> {
        try {

            clearService.clearAll();

            ctx.status(200);

        }catch(DataAccessException exception) {
            if (exception.getMessage().equals("Error: unauthorized")) {
                ctx.status(401);
                ctx.result("{\"message\":\"Error: unauthorized\"}");
            }
        }

    };
}
