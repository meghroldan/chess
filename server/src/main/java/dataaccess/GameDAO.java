package dataaccess;

import model.GameData;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.util.HashMap;

public interface GameDAO {

    void clear();

    GameCreationResult createGame(GameCreationRequest gameCreationRequest);

    HashMap<Integer, GameData> listGames();

    GameData getGame(Integer gameID) throws DataAccessException;

    void updateGame(String playColor, Integer gameID, String username) throws DataAccessException;
}
