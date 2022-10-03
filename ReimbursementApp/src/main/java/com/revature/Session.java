package com.revature;

public class Session {
    public enum Level {USER, ADMIN, NONE}

    Level level = Level.NONE;
    String curUsername = null;
    long curUserFK = -1;



}

