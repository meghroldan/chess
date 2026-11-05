package dataaccess;

import model.UserData;

public class SQLUserDAO implements UserDAO{
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
