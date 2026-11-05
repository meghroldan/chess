package dataaccess;

import model.AuthData;

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

    }

    @Override
    public AuthData createAuth(AuthData auth) throws DataAccessException {
        isDatabaseMade();
        return null;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        isDatabaseMade();
        return null;
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        isDatabaseMade();

    }
}
