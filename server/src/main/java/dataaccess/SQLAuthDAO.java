package dataaccess;

import model.AuthData;

import static dataaccess.DatabaseManager.createDatabase;

public class SQLAuthDAO implements AuthDAO{

    public SQLAuthDAO() throws DataAccessException{
        createDatabase();
    }

    @Override
    public void clear() {

    }

    @Override
    public AuthData createAuth(AuthData auth) {
        return null;
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

    }
}
