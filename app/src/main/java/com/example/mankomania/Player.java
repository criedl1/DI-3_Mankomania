package com.example.mankomania;

import android.widget.ImageView;
import android.widget.TextView;

public class Player extends start_view{

    private ImageView figure;
    private int currentField;
    private int money;
    private int updatemoney;
    private TextView currentmoney;

    public Player(ImageView figure, TextView currentmoney) {
         currentField = 0;
        //money = 1005;
        this.figure = figure;
        this. currentmoney = currentmoney;
        currentmoney.setText(Integer.toString(1000000));

    }

    public void moveFields(int fields, int maxfields) {
        currentField = currentField + fields;
        currentField = currentField % maxfields;
    }

    public void addMoney(int amount) {

        money = Integer.parseInt(currentmoney.getText().toString());
        updatemoney = money + amount;
        currentmoney.setText(Integer.toString(updatemoney));
    }


    public void removeMoney(int amount) {
        money = Integer.parseInt(currentmoney.getText().toString());
        updatemoney = money - amount;
        currentmoney.setText(Integer.toString(updatemoney));
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

