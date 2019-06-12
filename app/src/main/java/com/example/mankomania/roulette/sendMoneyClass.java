package com.example.mankomania.roulette;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.mankomania.network.client.Client;
import com.google.gson.JsonObject;

public class sendMoneyClass {

   private static int moneyAmount; //for Server Queue Handler

    protected void sendMoneyChange(int rouletteResult) {

        setMoneyAmount(rouletteResult);
        JsonObject object = new JsonObject();
        object.addProperty("result", rouletteResult);
        object.addProperty("OPERATION", "ROULETTERESULT");
        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());
        LocalBroadcastManager.getInstance(Client.getMapView())
                .sendBroadcast(intent);
    }

    public static int getMoneyAmount() {
        return moneyAmount;
    }

    public static void setMoneyAmount(int newMoneyAmount) {
        moneyAmount = newMoneyAmount;
    }
}
