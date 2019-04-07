package com.example.mankomania;

public class Field {
    enum_color color;
    int value;

    public Field(enum_color color, int value) {
        this.color = color;
        this.value = value;
    }

    public enum_color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }
}
