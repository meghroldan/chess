package dataaccess;

import model.GameData;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.util.HashMap;

import static dataaccess.DatabaseManager.createDatabase;

public class SQLGameDAO implements GameDAO{

    private boolean created = false;

    private void isDatabaseMade() throws DataAccessException {
        if (!created) {
            createDatabase();
            created = true;
        }
    }


    @Override
    public void clear() throws DataAccessException {
        isDatabaseMade();

    }

    @Override
    public GameCreationResult createGame(GameCreationRequest gameCreationRequest) throws DataAccessException {
        isDatabaseMade();
        return null;
    }

    @Override
    public HashMap<Integer, GameData> listGames() throws DataAccessException {
        isDatabaseMade();
        return null;
    }

    @Override
    public GameData getGame(Integer gameID) throws DataAccessException {
        isDatabaseMade();
        return null;
    }

    @Override
    public void updateGame(String playColor, Integer gameID, String username) throws DataAccessException {
        isDatabaseMade();

    }
}
