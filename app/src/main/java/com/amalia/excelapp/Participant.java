package com.amalia.excelapp;

public class Participant {
    private String name;
    private String id;
    private String birthDate;
    private String birthPlace;
    private String gender;

    public Participant(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Participant(String name, String id, String birthDate, String birthPlace, String gender) {
        this.name = name;
        this.id = id;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
