package dataaccess;

import chess.ChessGame;
import model.GameData;
import model.UserData;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{

    private Integer nextGameNum = 1;

    private HashMap<Integer, GameData> gameList = new HashMap<>();

    public void clear() throws DataAccessException {
        gameList.clear();
        nextGameNum = 1;

    }

    @Override
    public GameCreationResult createGame(GameCreationRequest gameCreationRequest) {
        ChessGame game = new ChessGame(); //does this need to go elsewhere?

        GameData gameData = new GameData(nextGameNum, null, null, gameCreationRequest.gameName(), game);
        gameList.put(nextGameNum, gameData);
        nextGameNum++;
        GameCreationResult result = new GameCreationResult(gameData.gameID());
        return result;
    }

    @Override
    public void joinGame(String playerColor, Integer gameID) {

    }

    @Override
    public HashMap<Integer, GameData> listGames() {
        return null;
    }
}
