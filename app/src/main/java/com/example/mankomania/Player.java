package com.example.mankomania;

import android.widget.ImageView;

public class Player {

    private ImageView figure;
    private int currentField;
    private int money;

    public Player(ImageView figure) {
        currentField = 0;
        money = 1000000;
        this.figure = figure;
    }

    public void addMoney(int amount) {
        money = money+amount;
    }

    public void removeMoney(int amount) {
        money = money-amount;
    }

    public ImageView getFigure() {
        return figure;
    }


    public int getCurrentField() {
        return currentField;
    }

    public void setCurrentField(int currentField) {
        this.currentField = currentField;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

