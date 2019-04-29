package com.example.mankomania.Map;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Player {

    private ImageView figure;
    private int currentField;
    private int money;
    private int updatemoney;
    private TextView currentmoney;

    Player(ImageView figure, TextView currentmoney) {
        currentField = 0;
        //money = 1005;
        this.figure = figure;
        this. currentmoney = currentmoney;
        currentmoney.setText(Integer.toString(1000000));

    }

    void moveFields(int fields, int maxfields) {
        Log.i("MOVE", "Von Feld "+currentField+" zu "+(currentField + fields)% maxfields);
        currentField = (currentField + fields)% maxfields;
    }

    void addMoney(int amount) {

        money = Integer.parseInt(currentmoney.getText().toString());
        updatemoney = money + amount;
        currentmoney.setText(Integer.toString(updatemoney));
    }


    public void removeMoney(int amount) {
        money = Integer.parseInt(currentmoney.getText().toString());
        updatemoney = money - amount;
        currentmoney.setText(Integer.toString(updatemoney));
    }

    ImageView getFigure() {
        return figure;
    }


    int getCurrentField() {
        return currentField;
    }

    void setCurrentField(int currentField) {
        this.currentField = currentField;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        currentmoney.setText(Integer.toString(money));
    }

}

