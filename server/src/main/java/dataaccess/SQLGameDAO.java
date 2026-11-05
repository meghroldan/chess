package dataaccess;

import model.GameData;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.util.HashMap;

import static dataaccess.DatabaseManager.createDatabase;

public class SQLGameDAO implements GameDAO{

    public SQLGameDAO() throws DataAccessException{
        createDatabase();
    }

    @Override
    public void clear() {

    }

    @Override
    public GameCreationResult createGame(GameCreationRequest gameCreationRequest) {
        return null;
    }

    @Override
    public HashMap<Integer, GameData> listGames() {
        return null;
    }

    @Override
    public GameData getGame(Integer gameID) throws DataAccessException {
        return null;
    }

    @Override
    public void updateGame(String playColor, Integer gameID, String username) throws DataAccessException {

    }
}
