package dataaccess;

import model.UserData;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

import static dataaccess.DatabaseManager.createDatabase;

public class SQLUserDAO implements UserDAO{

    private boolean created = false;

    private void isDatabaseMade() throws DataAccessException {
        if (!created) {
            createDatabase();
            created = true;
        }
    }


    @Override
    public void createUser(UserData user) throws DataAccessException {
        isDatabaseMade();
        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "INSERT INTO userData (username, password, email) VALUES (?, ?, ?)")){

            String hashedPassword = BCrypt.hashpw(user.password(), BCrypt.gensalt());

            stmt.setString(1, user.username());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, user.email());

            stmt.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DataAccessException("Unable to create user", exception);
        }
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        isDatabaseMade();
        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "SELECT username, password, email FROM userData WHERE username = ?")){

            stmt.setString(1, username);


            try (var tableValue = stmt.executeQuery()) {

                if (tableValue.next()) {
                    UserData userData = new UserData(tableValue.getString("username"), tableValue.getString("password"), tableValue.getString("email"));
                    return userData;
                }
                return null;
            }

        } catch (SQLException exception) {
            throw new DataAccessException("Unable to get user");
        }
    }

    @Override
    public void clear() throws DataAccessException {
        isDatabaseMade();

        try(var conn = DatabaseManager.getConnection();
            var stmt = conn.prepareStatement(
                    "DELETE FROM userData")){

            stmt.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Unable to clear user");
        }

    }

    @Override
    public UserData getUserRegister(String username) throws DataAccessException {
        isDatabaseMade();
        return null;
    }
}
