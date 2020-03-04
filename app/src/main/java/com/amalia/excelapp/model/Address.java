package com.amalia.excelapp.model;

public class Address {
    private String street;
    private String kec;
    private String kab;
    private String prov;

    public Address(String street, String kec, String kab, String prov) {
        this.street = street;
        this.kec = kec;
        this.kab = kab;
        this.prov = prov;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getKec() {
        return kec;
    }

    public void setKec(String kec) {
        this.kec = kec;
    }

    public String getKab() {
        return kab;
    }

    public void setKab(String kab) {
        this.kab = kab;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }
}
