package Model;

import Interface.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserModel {

    String id;
    String firstName;
    String lastName;
    String email;
    String password;

    public UserModel(String id, String firstName, String lastName, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void getUserInfo(){
        System.out.println("UserID" + " " + this.id);
        System.out.println("FirstName" + " " + this.firstName);
        System.out.println("LastName" + " " + this.lastName);
        System.out.println("Email" + " " + this.email);
        System.out.println("Password" + " " + this.password);
    }

    public String getUserEmail(){
        return this.email;
    }

    public String getUserPassword(){
//        hash password
        return this.password;
    }

    public String getUserId(){
        return  this.id;
    }

}
