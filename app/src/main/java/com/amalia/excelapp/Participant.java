package com.amalia.excelapp;

public class Participant {
    private String name;
    private long nip;
    private long nik;
    private String email;
    private int numPhone;

    private String birthDate;
    private String birthPlace;
    private String gender;

    private Position position;
    private String lastEducation;
    private String lastMajor;
    private String workPlace;

    private Address work;
    private Address personal;
    private int postalCode;

    public Participant(){}

    public Participant(String name, long nip, long nik, String email, int numPhone, String birthDate, String birthPlace, String gender, Position position, String lastEducation, String lastMajor, String workPlace, Address work, Address personal, int postalCode) {
        this.name = name;
        this.nip = nip;
        this.nik = nik;
        this.email = email;
        this.numPhone = numPhone;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.gender = gender;
        this.position = position;
        this.lastEducation = lastEducation;
        this.lastMajor = lastMajor;
        this.workPlace = workPlace;
        this.work = work;
        this.personal = personal;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNip() {
        return nip;
    }

    public void setNip(long nip) {
        this.nip = nip;
    }

    public long getNik() {
        return nik;
    }

    public void setNik(long nik) {
        this.nik = nik;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(int numPhone) {
        this.numPhone = numPhone;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(String lastEducation) {
        this.lastEducation = lastEducation;
    }

    public String getLastMajor() {
        return lastMajor;
    }

    public void setLastMajor(String lastMajor) {
        this.lastMajor = lastMajor;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public Address getWork() {
        return work;
    }

    public void setWork(Address work) {
        this.work = work;
    }

    public Address getPersonal() {
        return personal;
    }

    public void setPersonal(Address personal) {
        this.personal = personal;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
