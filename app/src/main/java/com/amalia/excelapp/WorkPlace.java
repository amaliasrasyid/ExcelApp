package com.amalia.excelapp;

public class WorkPlace {
    private String name;
    private Address address;
    private int postalCode;

    public WorkPlace(String name, Address address, int postalCode) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
