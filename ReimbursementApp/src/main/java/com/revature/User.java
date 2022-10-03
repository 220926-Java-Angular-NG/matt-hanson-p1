package com.revature;

public class User {

    long id = 0;
    String username = "";
    String password = "";
    Session.Level level = Session.Level.USER;

    User(){

    }

    User(String username){
        this.username = username;
        this.password = "pass1234";
    }

    User(long id, String username, String password, boolean isAdmin){
        this.id = id;
        this.username = username;
        this.password = password;

        if (isAdmin)
            this.level = Session.Level.ADMIN;
        else
            this.level = Session.Level.USER;
    }

}
