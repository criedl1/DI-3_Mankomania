package com.example.mankomania;

public class FieldClass {
    ColorEnum color;
    int value;
    float degree;

    public FieldClass(ColorEnum color, int value, float degree) {
        this.color = color;
        this.value = value;
        this.degree = degree;
    }

    public ColorEnum getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public float getDegree(){
        return this.degree;
    }
}
