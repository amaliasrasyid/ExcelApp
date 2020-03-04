package com.amalia.excelapp.model;

public class Education {
    String eduLevel;
    String major;
    String faculty;

    public Education(String eduLevel, String major, String faculty) {
        this.eduLevel = eduLevel;
        this.major = major;
        this.faculty = faculty;
    }

    public Education(String eduLevel, String major) {
        this.eduLevel = eduLevel;
        this.major = major;
    }

    public Education(){}

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
