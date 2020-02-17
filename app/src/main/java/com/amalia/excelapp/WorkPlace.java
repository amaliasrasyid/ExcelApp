package com.amalia.excelapp;

public class WorkPlace {
    private String streetName;
    private Address work;
    private int postalCode;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Address getWork() {
        return work;
    }

    public void setWork(Address work) {
        this.work = work;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
