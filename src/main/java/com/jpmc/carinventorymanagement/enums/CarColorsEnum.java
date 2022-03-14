package com.jpmc.carinventorymanagement.enums;

public enum CarColorsEnum {
    WHITE("White"),
    BLACK("Black"),
    ORANGE("Orange"),
    BLUE("Blue"),
    GREY("Grey");

    private String color;

    CarColorsEnum(final String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
