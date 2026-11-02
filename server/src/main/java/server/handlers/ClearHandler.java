package server.handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import io.javalin.http.Handler;
import request.RegisterRequest;
import result.RegisterResult;
import service.ClearService;

public class ClearHandler {
    private ClearService clearService;

    public ClearHandler(ClearService clearService1){
        clearService = clearService1;
    }

    public Handler clear = ctx -> {
        try {
            Gson gson = new Gson();
            String body = ctx.body();

            clearService.clearAll();

            ctx.status(200);

        }catch(DataAccessException exception) {
            if (exception.getMessage().equals("Error: unauthorized")) {
                ctx.status(401);
                ctx.result("{\"message\":\"Error: unauthorized\"}"); // TODO maybe json this ctx.result(gson.toJson(exception.getMessage())
            }
        }

    };
}
