package Interface;

import Model.ActivityModel;

import java.util.List;

public interface User {
    String SignUp(String firstName, String lastName, String email, String password);
    String LogIn(String email, String password) throws Exception;

    void FollowUser(String followUserId) throws Exception;

}
