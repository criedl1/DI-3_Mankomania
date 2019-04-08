package com.example.mankomania;

public class FieldClass {
    ColorEnum color;
    int value;

    public FieldClass(ColorEnum color, int value) {
        this.color = color;
        this.value = value;
    }

    public ColorEnum getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }
}
