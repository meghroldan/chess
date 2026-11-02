package dataaccess;

import model.AuthData;
import model.UserData;

import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO{

    private HashMap<String, AuthData> authList = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        authList.clear();

    }

    @Override
    public AuthData createAuth(AuthData auth) throws DataAccessException {
        authList.put(auth.authToken(),auth);
        return authList.get(auth);

    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        if(authList.containsKey(authToken)){
            return authList.get(authToken);
        }
        else{
            throw new DataAccessException("Error: unauthorized");
        }
    }

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
