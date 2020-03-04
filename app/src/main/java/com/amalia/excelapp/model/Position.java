package com.amalia.excelapp.model;

public class Position {
    private String name;
    private String echelon;

    public Position(String name, String echelon) {
        this.name = name;
        this.echelon = echelon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEchelon() {
        return echelon;
    }

    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }
}
