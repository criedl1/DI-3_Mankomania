package com.example.mankomania.Roulette;

class FieldClass {
    private ColorEnum color;
    private int value;
    private float degree;

    FieldClass(ColorEnum color, int value, float degree) {
        this.color = color;
        this.value = value;
        this.degree = degree;
    }

    ColorEnum getColor() {
        return color;
    }

    int getValue() {
        return value;
    }

    float getDegree(){
        return this.degree;
    }
}
