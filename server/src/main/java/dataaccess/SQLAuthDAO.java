package dataaccess;

import model.AuthData;
import model.UserData;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

import static dataaccess.DatabaseManager.createDatabase;

public class SQLAuthDAO implements AuthDAO{

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
        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "DELETE FROM authData")){

            stmt.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Unable to clear authData");
        }

    }

    @Override
    public AuthData createAuth(AuthData auth) throws DataAccessException {
        isDatabaseMade();
        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "INSERT INTO authData (authToken, username) VALUES (?, ?)")){

            stmt.setString(1, auth.authToken());
            stmt.setString(2, auth.username());

            stmt.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Unable to create auth");
        }
        return null;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        isDatabaseMade();
        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "SELECT authToken, username FROM authData WHERE authToken = ?")){

            stmt.setString(1, authToken);


            try (var tableValue = stmt.executeQuery()) {

                if (tableValue.next()) {
                    AuthData authData = new AuthData(tableValue.getString("authToken"), tableValue.getString("username"));
                    return authData;
                }
            }
            throw new DataAccessException("Error: unauthorized");

        } catch (SQLException exception) {
            throw new DataAccessException("Unable to get authToken");
        }
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        isDatabaseMade();
        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "DELETE FROM authData WHERE authToken = ?")){

            stmt.setString(1, authToken);


            //stmt.executeUpdate();

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new DataAccessException("Error: unauthorized");
            }

        } catch (SQLException exception) {
            throw new DataAccessException("Unable to delete authToken");
        }




    }
}
