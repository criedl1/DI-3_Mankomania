package com.example.mankomania.map;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Locale;

public class Player {

    private ImageView figure;
    private int currentField;
    private int money;
    private TextView currentmoney;
    private int[] aktien = new int[3];

    Player() {
        currentField = 0;
        this.money = 1000000;
        Arrays.fill(aktien,0);
    }

    void initFields(ImageView figure, TextView currentmoney){
        this.figure = figure;
        this.currentmoney = currentmoney;
        this.updateMoneyField();
    }

    void moveFields(int fields, int maxfields) {
        // Log.i("MOVE", "Player "+index+" von Feld "+currentField+" zu "+(currentField + fields)% maxfields);
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
        this.updateMoneyField();
    }

    public void updateMoneyField(){
        this.currentmoney.setText(String.format(Locale.GERMAN,"%d",this.money));
    }


    public void setPosition(int position) {
        this.currentField = position;
    }

    void setAktie(Aktien aktien, int count) {
        switch(aktien){
            case HYPO:
                this.aktien[0]= count;
                break;
            case STRABAG:
                this.aktien[1] = count;
                break;
            case INFINEON:
                this.aktien[2] = count;
                break;
        }
    }
}

