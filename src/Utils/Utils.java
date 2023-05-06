package Utils;

import Model.UserModel;

import java.util.HashMap;
import java.util.UUID;

public final class Utils {

    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static UserModel getUserWithEmail(String email, HashMap<String, UserModel> userMap) throws Exception {
        for(UserModel user : userMap.values()){
            if(user.getUserEmail().equals(email)){
                return user;
            }
        }
        throw new Exception("User With the given Email not found!!");
    }



}
