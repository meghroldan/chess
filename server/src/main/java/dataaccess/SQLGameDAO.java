package dataaccess;

import chess.ChessGame;
import chess.ChessPiece;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import model.GameData;
import model.UserData;
import org.mindrot.jbcrypt.BCrypt;
import request.GameCreationRequest;
import result.GameCreationResult;

import java.sql.SQLException;
import java.util.HashMap;

import static dataaccess.DatabaseManager.createDatabase;

public class SQLGameDAO implements GameDAO{

    private boolean created = false;
    private Integer nextGameNum = 1;

    private void isDatabaseMade() throws DataAccessException {
        if (!created) {
            createDatabase();
            created = true;
        }
    }


    @Override
    public void clear() throws DataAccessException {
        isDatabaseMade();
        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "DELETE FROM gameData")){

            stmt.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Unable to clear authData");
        }

    }

    @Override
    public GameCreationResult createGame(GameCreationRequest gameCreationRequest) throws DataAccessException {
        isDatabaseMade();
        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "INSERT INTO gameData (gameID, whiteUsername, blackUsername, gameName, game) VALUES (?, ?, ?, ?, ?)")){


            stmt.setInt(1, nextGameNum);
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setString(4, gameCreationRequest.gameName());

            ChessGame game = new ChessGame();
            Gson gson = new Gson();
            String jsonGame = gson.toJson(game);

            stmt.setString(5, jsonGame);

            stmt.executeUpdate();

            GameCreationResult gameCreationResult = new GameCreationResult(nextGameNum);
            nextGameNum++;
            return gameCreationResult;

        } catch (SQLException exception) {
            //exception.printStackTrace();
            throw new DataAccessException("Error: already taken");
        }
    }

    @Override
    public HashMap<Integer, GameData> listGames() throws DataAccessException {
        isDatabaseMade();
        HashMap<Integer, GameData> list = new HashMap<>();


        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "SELECT * FROM gameData")){


            try (var tableValue = stmt.executeQuery()) {

                Gson gson = new Gson();
                while(tableValue.next()) {

                    int gameID = tableValue.getInt("gameID");
                    String whiteUser = tableValue.getString("whiteUsername");
                    String blackUser = tableValue.getString("blackUsername");
                    String name = tableValue.getString("gameName");
                    String gameString = tableValue.getString("game");

                    ChessGame game = gson.fromJson(gameString, ChessGame.class);

                    GameData gameData = new GameData(gameID, whiteUser, blackUser, name, game);
                    list.put(gameID, gameData);


                }
                return list;
            }

        } catch (SQLException exception) {
            //exception.printStackTrace();
            throw new DataAccessException("Error: already taken");
        }
    }

    @Override
    public GameData getGame(Integer gameID) throws DataAccessException {
        isDatabaseMade();

        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "SELECT gameID, whiteUsername, blackUsername, gameName, game FROM gameData WHERE gameID = ?")){

            stmt.setInt(1, gameID);

            try (var tableValue = stmt.executeQuery()) {

                if(tableValue.next()) {

                    int gameIDtable = tableValue.getInt("gameID");
                    String whiteUser = tableValue.getString("whiteUsername");
                    String blackUser = tableValue.getString("blackUsername");
                    String name = tableValue.getString("gameName");
                    String gameString = tableValue.getString("game");

                    Gson gson = new Gson();
                    ChessGame game = gson.fromJson(gameString, ChessGame.class);

                    GameData gameData = new GameData(gameIDtable, whiteUser, blackUser, name, game);
                    return gameData;


                }
                throw new DataAccessException("Error: bad request");
            }

        } catch (SQLException exception) {
            //exception.printStackTrace();
            throw new DataAccessException("Error: bad request");
        }
    }

    @Override
    public void updateGame(String playColor, Integer gameID, String username) throws DataAccessException {
        isDatabaseMade();


        if(playColor.equals("WHITE")) {
            try (var conn = DatabaseManager.getConnection();
                 var stmt = conn.prepareStatement(
                         "UPDATE gameDATA SET whiteUsername = ? WHERE gameID = ?")) {

                stmt.setString(1, username);
                stmt.setInt(2, gameID);
                stmt.executeUpdate();


            } catch (SQLException exception) {
                //exception.printStackTrace();
                throw new DataAccessException("Error: bad request");
            }
        }

        if(playColor.equals("BLACK")) {
            try (var conn = DatabaseManager.getConnection();
                 var stmt = conn.prepareStatement(
                         "UPDATE gameDATA SET blackUsername = ? WHERE gameID = ?")) {

                stmt.setString(1, username);
                stmt.setInt(2, gameID);
                stmt.executeUpdate();


            } catch (SQLException exception) {
                //exception.printStackTrace();
                throw new DataAccessException("Error: bad request");
            }
        }

    }
}
