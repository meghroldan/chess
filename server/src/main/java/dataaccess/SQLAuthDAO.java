package dataaccess;

import model.AuthData;

public class SQLAuthDAO implements AuthDAO{
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
