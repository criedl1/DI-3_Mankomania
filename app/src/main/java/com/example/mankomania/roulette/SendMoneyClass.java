package com.example.mankomania.roulette;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.example.mankomania.network.client.Client;
import com.google.gson.JsonObject;

public class SendMoneyClass {

   private static int moneyAmount; //for Server Queue Handler

    protected void sendMoneyChange(int casinoResult) {

        JsonObject object = new JsonObject();
        object.addProperty("result", casinoResult);
        object.addProperty("OPERATION", "ROULETTERESULT");

        JsonObject object1 = new JsonObject();
        object1.addProperty("result", casinoResult);
        object1.addProperty("PLAYER", 0);
        object1.addProperty("OPERATION", "sendCasino");

        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());

        Intent intent2 = new Intent("client.update");
        intent2.putExtra("result", object1.toString());
        LocalBroadcastManager.getInstance(Client.getMapView())
                .sendBroadcast(intent);
        LocalBroadcastManager.getInstance(Client.getMapView())
                .sendBroadcast(intent2);
    }
}
