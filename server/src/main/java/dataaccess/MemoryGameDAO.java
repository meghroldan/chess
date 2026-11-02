package dataaccess;

import chess.ChessGame;
import model.GameData;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.util.HashMap;

/**
 * Handles all memory and stores data for games
 */
public class MemoryGameDAO implements GameDAO{

    private Integer nextGameNum = 1;

    private final HashMap<Integer, GameData> gameList = new HashMap<>();

    /**
     * Clears gameList to reset
     */
    public void clear(){
        gameList.clear();
        nextGameNum = 1;

    }

    /**
     * Takes in GameCreationRequest and creates a game to add to the gameList
     * @param gameCreationRequest gameName
     * @return GameCreationResult
     */
    @Override
    public GameCreationResult createGame(GameCreationRequest gameCreationRequest){
        ChessGame game = new ChessGame();

        GameData gameData = new GameData(nextGameNum, null, null, gameCreationRequest.gameName(), game);
        gameList.put(nextGameNum, gameData);
        nextGameNum++;

        return new GameCreationResult(gameData.gameID());
    }


    /**
     * Returns all games in gameList
     * @return gameList
     */
    @Override
    public HashMap<Integer, GameData> listGames(){
        return gameList;
    }


    /**
     * Takes in gameID and returns GameData associated with the gameID
     * @param gameID key for map
     * @return GameData
     * @throws DataAccessException if bad request
     */
    @Override
    public GameData getGame(Integer gameID) throws DataAccessException {
        if(gameList.containsKey(gameID)){
            return gameList.get(gameID);
        }
        else{
            throw new DataAccessException("Error: bad request");
        }
    }

    /**
     * Takes in color, gameID, and username to update and add player to game
     * @param playColor playerColor
     * @param gameID gameID - map key
     * @param username username
     * @throws DataAccessException throws if game already has user of that color
     */
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
