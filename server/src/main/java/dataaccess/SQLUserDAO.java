package dataaccess;

import model.UserData;

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

            stmt.setString(1, user.username());
            stmt.setString(2, user.password());
            stmt.setString(3, user.email());

            stmt.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Unable to create user");
        }
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        isDatabaseMade();
        return null;
    }

    @Override
    public void clear() throws DataAccessException {
        isDatabaseMade();
    }

    @Override
    public UserData getUserRegister(String username) throws DataAccessException {
        isDatabaseMade();
        return null;
    }
}
