package com.example.mankomania.roulette;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.mankomania.network.client.Client;
import com.google.gson.JsonObject;

public class RouletteLogic {

    private RouletteClass roulette;
    private FieldClass[] fieldArray;
    private String returnString;
    private int money;
    int rouletteNumber;

    //for Network
    private static int moneyAmount;

    public RouletteLogic(){
        spinRoulette();
    }

    protected RouletteLogic(int choosenNumber){
        spinRoulette();
        checkNumber(choosenNumber);
    }

    protected RouletteLogic(ColorEnum choosenColor){
        spinRoulette();
        checkColor(choosenColor);
    }

    protected RouletteLogic(String choosenDozen){
        spinRoulette();
        checkDozen(Integer.parseInt(choosenDozen));

}
    public void spinRoulette(){
        roulette = new RouletteClass();
        roulette.setUpFields();
        fieldArray = roulette.getFieldClassArray();
        rouletteNumber = roulette.spinIt();
    }

    public void checkColor(ColorEnum choosenColor) {
        for (FieldClass anArray : fieldArray) {
            if (rouletteNumber == anArray.getValue()) {
                if (anArray.getColor() == choosenColor) {
                    setMoney(30000); //--> 80000-50000 Einsatz
                    setReturnString("Du hast " +  getMoney() + " gewonnen.");
                    moneyAmount = getMoney();

                } else {
                    setMoney(-5000); //Einsatz
                    setReturnString("Du hast " +  getMoney() * -1 + " verloren.");
                    moneyAmount = getMoney();
                }
                //sendMoneyChange(getMoney());
            }
        }
    }

    public int checkNumber(int choosenNumber) {
        if (rouletteNumber == choosenNumber) {
            setMoney(145000);  //--> 150000 - 5000 Einsatz
            moneyAmount = getMoney();
            setReturnString("Du hast " +  getMoney() + " gewonnen.");
        } else {
            setMoney(- 50000); //Einsatz
            moneyAmount = getMoney();
            setReturnString("Du hast " +  getMoney() * -1 + " verloren.");
        }
        //this.sendMoneyChange(getMoney());
        return money;
    }

    public void checkDozen(int choosenDozen){
        int dozen = 0;

        for (FieldClass fieldClass : fieldArray) {
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
            setReturnString("Du hast " +  getMoney() + " gewonnen.");
            moneyAmount = getMoney();}
        else {
            setMoney(- 20000);
            setReturnString("Du hast " +  getMoney() * -1 + " verloren.");
            moneyAmount = getMoney();
        }
        //this.sendMoneyChange(getMoney());
    }

    public int getRandomNumberFromRoulette() {
        return roulette.getRandomNumber();
    }

    public float getDegreeFromRoulette() {
        return roulette.getTheField().getDegree();
    }

    public ColorEnum getColorFromRoulette() {
        return roulette.getTheField().getColor();
    }

    public String getColorString(){
        return roulette.getTheField().getColor().toString();
    }

    /*
    private void sendMoneyChange(int rouletteResult) {
        JsonObject object = new JsonObject();
        object.addProperty("result", rouletteResult);
        object.addProperty("OPERATION", "ROULETTERESULT");
        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());
        LocalBroadcastManager.getInstance(Client.mapView)
                .sendBroadcast(intent);
    }*/

    public int getMoney() {
        return money;
    }

    public void setMoney(int newMoney) {
        money = newMoney;
    }

    public String getReturnString() {
        return returnString;
    }

    public void setReturnString(String returnString) {
        this.returnString = returnString;
    }

    public static int getMoneyAmount() {
        return moneyAmount;
    }

    public RouletteClass getRoulette(){
        return roulette;
    }

    public FieldClass[] getFieldArray() {
        return fieldArray;
    }

    public int getRouletteNumber() {
        return rouletteNumber;
    }
}
