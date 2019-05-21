package com.example.mankomania.roulette;

public class FieldClass {
    private ColorEnum color;
    private int value;
    private float degree;

    FieldClass(ColorEnum color, int value, float degree) {
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