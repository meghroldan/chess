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
    public GameCreationResult createGame(GameCreationRequest gameCreationRequest) throws DataAccessException {
        ChessGame game = new ChessGame(); //does this need to go elsewhere?

        GameData gameData = new GameData(nextGameNum, null, null, gameCreationRequest.gameName(), game);
        gameList.put(nextGameNum, gameData);
        nextGameNum++;
        GameCreationResult result = new GameCreationResult(gameData.gameID());
        return result;
    }

    @Override
    public void joinGame(String playerColor, Integer gameID) throws DataAccessException  {

    }

    @Override
    public HashMap<Integer, GameData> listGames() throws DataAccessException  {
        return null;
    }

    @Override
    public GameData getGame(Integer gameID) throws DataAccessException {
        if(gameList.containsKey(gameID)){
            return gameList.get(gameID);
        }
        else{
            throw new DataAccessException("Error: bad request");
        }
    }

    @Override
    public void updateGame(String playColor, Integer gameID, String username) throws DataAccessException {
        GameData gameDataOld = getGame(gameID);
        GameData gameDataNew = gameDataOld;
        if(playColor.equals("WHITE")){
            gameDataNew = new GameData(gameID, username, gameDataOld.blackUsername(), gameDataOld.gameName(), gameDataOld.game());
        }
        if(playColor.equals("BLACK")){
            gameDataNew = new GameData(gameID, gameDataOld.whiteUsername(), username, gameDataOld.gameName(), gameDataOld.game());
        }
        gameList.put(gameID, gameDataNew);
    }
}
