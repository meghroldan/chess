package dataaccess;

import model.GameData;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.util.HashMap;

public interface GameDAO {

    void clear() throws DataAccessException;

    GameCreationResult createGame(GameCreationRequest gameCreationRequest) throws DataAccessException;

    HashMap<Integer, GameData> listGames() throws DataAccessException;

    GameData getGame(Integer gameID) throws DataAccessException;

    void updateGame(String playColor, Integer gameID, String username) throws DataAccessException;
}
