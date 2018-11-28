package com.example.recytechco.models;

public class History {

    private int mElementId;
    private String mElementName;
    private int mAmount;
    private String mPlace;
    private String mDateInString;
    private int mUserId;

    public History(int elementId, String elementName, int amount, String place, String dateInString,
                   int userId) {
        mElementId = elementId;
        mElementName = elementName;
        mAmount = amount;
        mPlace = place;
        mDateInString = dateInString;
        mUserId = userId;
    }

    public History(int elementId, String elementName, int amount, String place, String dateInString) {
        mElementId = elementId;
        mElementName = elementName;
        mAmount = amount;
        mPlace = place;
        mDateInString = dateInString;
    }


    public int getElementId() {
        return mElementId;
    }

    public void setElementName(int elementId) {
        mElementId = elementId;
    }

    public String getElementName() {
        return mElementName;
    }

    public void setElementName(String elementName) {
        mElementName = elementName;
    }

    public int getAmount() {
        return mAmount;
    }

    public void setAmount(int amount) {
        mAmount = amount;
    }

    public String getPlace() {
        return mPlace;
    }

    public void setPlace(String place) {
        mPlace = place;
    }

    public int getUserId() {
        return mUserId;
    }

    public String getDateInString() {
        return mDateInString;

    }

    public void setDateInString(String dateInString) {
        mDateInString = dateInString;
    }


}
