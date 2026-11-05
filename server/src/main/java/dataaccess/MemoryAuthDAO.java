package dataaccess;

import model.AuthData;

import java.util.HashMap;

/**
 * Handles all memory and stores data for authentication
 */
public class MemoryAuthDAO implements AuthDAO{

    private final HashMap<String, AuthData> authList = new HashMap<>();

    /**
     * Clears authList to reset
     */
    @Override
    public void clear(){
        authList.clear();

    }

    /**
     * Takes in authData and puts it in the authList sorted by authToken
     * @param auth AuthData
     * @return AuthData
     */
    @Override
    public AuthData createAuth(AuthData auth){
        authList.put(auth.authToken(),auth);
        return authList.get(auth);

    }

    /**
     * Takes in authToken and returns the AuthData associated with it or throws an error
     * @param authToken authToken
     * @return AuthData
     * @throws DataAccessException if cannot find authToken and data
     */
    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        if(authList.containsKey(authToken)){
            return authList.get(authToken);
        }
        else{
            throw new DataAccessException("Error: unauthorized");
        }
    }

    /**
     * Takes in authToken and deletes the single use of it
     * @param authToken authToken
     * @throws DataAccessException if cannot find authToken
     */
    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

        //TODO write test authtoken not there
        if(authList.containsKey(authToken)){
            authList.remove(authToken);
        }
        else{
            throw new DataAccessException("Error: unauthorized");
        }

    }
}
