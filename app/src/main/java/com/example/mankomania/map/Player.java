package com.example.mankomania.map;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Player {

    private int index;
    private ImageView figure;
    private int currentField;
    private int money;
    private TextView currentmoney;

    Player(ImageView figure, TextView currentmoney) {
        currentField = 0;
        this.figure = figure;
        this. currentmoney = currentmoney;
        currentmoney.setText(Integer.toString(1000000));
    }

    void moveFields(int fields, int maxfields) {
        Log.i("MOVE", "Player "+index+" von Feld "+currentField+" zu "+(currentField + fields)% maxfields);
        currentField = (currentField + fields)% maxfields;
    }

    ImageView getFigure() {
        return figure;
    }

    int getCurrentField() {
        return currentField;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

