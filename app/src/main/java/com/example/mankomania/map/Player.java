package com.example.mankomania.map;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Locale;

public class Player {

    private ImageView figure;
    private int currentField;
    private int money = 0;
    private TextView currentmoney;
    private int[] aktien = new int[3];
    private String ip;

    Player() {
        currentField = 0;
        Arrays.fill(aktien,0);
    }

    void initFields(ImageView figure, TextView currentmoney){
        this.figure = figure;
        this.currentmoney = currentmoney;
        this.updateMoneyField();
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

    private void updateMoneyField(){
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
            default:
                throw new IllegalStateException("Aktie does not exist");
        }
    }

    void setIP(String ip) {
        this.ip = ip;
    }
}

