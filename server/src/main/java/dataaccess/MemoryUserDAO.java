package dataaccess;

import model.UserData;

import java.util.HashMap;

/**
 * Handles all memory and stores data for user
 */
public class MemoryUserDAO implements UserDAO{
    private final HashMap<String, UserData> userList = new HashMap<>();


    /**
     * Creates a user if username is not taken already
     * @param user includes username, password, and email
     * @throws DataAccessException if username is taken
     */
    @Override
    public void createUser(UserData user) throws DataAccessException {
        if(userList.containsKey(user.username())){
            throw new DataAccessException("Error: already taken");
        }
        userList.put(user.username(), user);

    }

    /**
     * Returns a user if they are in data
     * @param username username
     * @return UserData
     * @throws DataAccessException if user is not there
     */
    @Override
    public UserData getUser(String username) throws DataAccessException {
        if(userList.containsKey(username)){
            return userList.get(username);
        }
        else{
            throw new DataAccessException("Error: unauthorized");
        }
    }

    /**
     * When registering for the first time, check if the user is there or not.
     * This is where we do not call an error if the user is there already since it is a different
     * kind of error
     * @param username username
     * @return UserData
     */
    public UserData getUserRegister(String username){
        if(userList.containsKey(username)){
            return userList.get(username);
        }
        else{
            return null;
        }
    }

    /**
     * Clears userList to reset
     */
    @Override
    public void clear() {
        userList.clear();

    }
}
