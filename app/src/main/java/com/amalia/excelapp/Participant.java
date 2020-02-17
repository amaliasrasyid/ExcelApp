package com.amalia.excelapp;

public class Participant {
    private String name;
    private String nipId;
    private String nikId;
    private String birthDate;
    private String birthPlace;
    private String gender;
    private String email;
    private String phoneNum;
    private Address personal;

    private WorkPlace workPlace;

    private Education education;

    private Position position;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNipId() {
        return nipId;
    }

    public void setNipId(String nipId) {
        this.nipId = nipId;
    }

    public String getNikId() {
        return nikId;
    }

    public void setNikId(String nikId) {
        this.nikId = nikId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumPhone() {
        return phoneNum;
    }

    public void setNumPhone(String numPhone) {
        this.phoneNum = numPhone;
    }

    public Address getPersonal() {
        return personal;
    }

    public void setPersonal(Address personal) {
        this.personal = personal;
    }

    public WorkPlace getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
