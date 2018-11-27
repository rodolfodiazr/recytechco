package com.example.recytechco.models;

public class Element {

    private int mId;
    private String mName;
    private int mPoints;

    public Element(int id, String name, int points) {
        mId = id;
        mName = name;
        mPoints = points;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int points) {
        mPoints = points;
    }
}