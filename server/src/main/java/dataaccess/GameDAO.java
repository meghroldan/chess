package dataaccess;

import model.GameData;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.util.HashMap;

public interface GameDAO {

    void clear() throws DataAccessException;

    GameCreationResult createGame(GameCreationRequest gameCreationRequest);

    void joinGame(String playerColor, Integer gameID);

    HashMap<Integer, GameData> listGames();
}
