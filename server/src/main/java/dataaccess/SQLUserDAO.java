package dataaccess;

import model.UserData;

import static dataaccess.DatabaseManager.createDatabase;

public class SQLUserDAO implements UserDAO{

    public SQLUserDAO() throws DataAccessException{
        createDatabase();
    }

    @Override
    public void createUser(UserData user) throws DataAccessException {

    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public UserData getUserRegister(String username) {
        return null;
    }
}
