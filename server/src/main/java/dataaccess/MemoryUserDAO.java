package dataaccess;

import model.UserData;

import java.util.HashMap;

public class MemoryUserDAO implements UserDAO{
    private HashMap<String, UserData> userList = new HashMap<>();

    @Override
    public void createUser(UserData user) throws DataAccessException {
        if(userList.containsKey(user.username())){
            throw new DataAccessException("Error: already taken");
        }
        userList.put(user.username(), user);

    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        if(userList.containsKey(username)){
            return userList.get(username);
        }
        else{
            throw new DataAccessException("Error: unauthorized");
        }
        //return userList.get(username);
    }

    public UserData getUserRegister(String username){
        if(userList.containsKey(username)){
            return userList.get(username);
        }
        else{
            return null;
        }
    }

    @Override
    public void clear() throws DataAccessException {
        userList.clear();

    }
}
