package com.example.recytechco.models;

public class User {

    private int mId;
    private String mUsername;
    private String mFullName;
    private String mPassword;
    private int mPoints;

    public User(String username, String fullName, String password, int points) {
        mUsername = username;
        mFullName = fullName;
        mPassword = password;
        mPoints = points;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int points) {
        this.mPoints = points;
    }
}