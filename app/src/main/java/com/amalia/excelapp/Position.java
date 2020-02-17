package com.amalia.excelapp;

public class Position {
    private String name;
    private String group;
    private String echelon;

    public Position(String name, String group, String echelon) {
        this.name = name;
        this.group = group;
        this.echelon = echelon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEchelon() {
        return echelon;
    }

    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }
}
