package Model;

import Utils.Utils;

public class UserSession {
    String userId;
    long createdAt;
    boolean isValid;

    int expirationInMinutes = 1440;

    public UserSession(String userId){
        this.userId = userId;
        this.createdAt = System.currentTimeMillis();
        this.isValid = true;
    }

    public String getUserIdFromSession() throws Exception {
        if(checkIsSessionValid()){
            return this.userId;
        }
        throw new Exception("USER SESSION HAS EXPIRED");
    }

    boolean checkIsSessionValid(){
        long elapsedTimeInMinutes = (System.currentTimeMillis() - this.createdAt) / (1000 * 60);
        if (elapsedTimeInMinutes <= this.expirationInMinutes) {
            // Session is still valid
            return true;
        } else {
            // Session has expired
            this.isValid = false;
            return false;
        }

    }

}
