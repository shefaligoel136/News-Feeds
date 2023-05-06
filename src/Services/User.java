package Services;

import Interface.User;

public class UserAuth implements User {

    String firstName;
    String lastName;
    String email;
    String password;

    UserAuth() {
//        constructor
    }

    void SignUp(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password; // TODO: HASH
    }
}
