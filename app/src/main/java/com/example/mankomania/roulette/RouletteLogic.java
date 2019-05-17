package com.example.mankomania.roulette;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.mankomania.network.client.Client;
import com.google.gson.JsonObject;

public class RouletteLogic {

    private RouletteClass roulette;
    private FieldClass[] array;
    private String returnString;
    private int money;
    int rouletteNumber;

    //for Network
    private static int moneyAmount;

    public RouletteLogic(int choosenNumber){
        checkNumber(choosenNumber);
    }

    public RouletteLogic(ColorEnum choosenColor){
        checkColor(choosenColor);
    }

    public RouletteLogic(String choosenDozen){
        checkDozen(Integer.parseInt(choosenDozen));

}
    public void spinRoulette(){
        roulette = new RouletteClass();
        array = roulette.setUpFields();
        rouletteNumber = roulette.spinIt();
    }

    public void checkColor(ColorEnum choosenColor) {
        spinRoulette();
        for (FieldClass anArray : array) {
            if (rouletteNumber == anArray.getValue()) {
                if (anArray.getColor() == choosenColor) {
                    setMoney(30000); //--> 80000-50000 Einsatz
                    //setReturnString(getString(R.string.roulette_won, getMoney()));
                    moneyAmount = getMoney();

                } else {
                    setMoney(-5000); //Einsatz
                    //setReturnString(getString(R.string.roulette_lost, getMoney() * -1));
                    moneyAmount = getMoney();
                }
                sendMoneyChange(getMoney());
            }
        }
    }

    public int checkNumber(int choosenNumber) {
        spinRoulette();
        if (rouletteNumber == choosenNumber) {
            setMoney(145000);  //--> 150000 - 5000 Einsatz
            moneyAmount = money;
            //returnString = getString(R.string.roulette_won, getMoney());
        } else {
            setMoney(- 50000); //Einsatz
            moneyAmount = getMoney();
            //returnString = getString(R.string.roulette_lost, getMoney()*-1);
        }
        this.sendMoneyChange(getMoney());
        return money;
    }

    public void checkDozen(int choosenDozen){
        spinRoulette();
        int dozen = 0;

        for (FieldClass fieldClass : array) {
            if (fieldClass.getValue() == rouletteNumber) {
                if (fieldClass.getValue() <= 12) {
                    dozen = 1;
                } else if (fieldClass.getValue() <= 24 && fieldClass.getValue() > 12) {
                    dozen = 2;
                } else {
                    dozen = 3;
                }
            }
        }

        if (choosenDozen == dozen){
            setMoney(80000); //100000 - 20000 Einsatz
            //setReturnString(getString(R.string.roulette_won, getMoney()));
            moneyAmount = getMoney();}
        else {
            setMoney(- 20000);
            //setReturnString(getString(R.string.roulette_lost, getMoney() * -1));
            moneyAmount = getMoney();
        }
        this.sendMoneyChange(getMoney());
    }

    private int getRandomNumberFromRouletteClass() {
        return roulette.getRandomNumber();
    }

    private float getDegreeFromRouletteClass() {
        return roulette.getTheField().getDegree();
    }

    private ColorEnum getColorFromRouletteClass() {
        return roulette.getTheField().getColor();
    }

    private void sendMoneyChange(int rouletteResult) {
        JsonObject object = new JsonObject();
        object.addProperty("result", rouletteResult);
        object.addProperty("OPERATION", "ROULETTERESULT");
        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());
        LocalBroadcastManager.getInstance(Client.mapView)
                .sendBroadcast(intent);
    }

    private int getMoney() {
        return money;
    }

    private void setMoney(int newMoney) {
        money = newMoney;
    }

    private String getReturnString() {
        return returnString;
    }

    private void setReturnString(String returnString) {
        this.returnString = returnString;
    }

    public static int getMoneyAmount() {
        //temporary solution
        return moneyAmount;
    }
}
