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

    protected ColorEnum getColor() {
        return color;
    }

    protected int getValue() {
        return value;
    }

    protected float getDegree(){
        return this.degree;
    }
}
