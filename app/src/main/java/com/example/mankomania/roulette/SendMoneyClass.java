package com.example.mankomania.roulette;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.mankomania.network.NetworkConstants;
import com.example.mankomania.network.client.Client;
import com.google.gson.JsonObject;

public class SendMoneyClass {

    protected void sendMoneyChange(int casinoResult) {

        JsonObject object = new JsonObject();
        object.addProperty(NetworkConstants.RESULT, casinoResult);
        object.addProperty(NetworkConstants.OPERATION, NetworkConstants.ROULETTE);

        JsonObject object1 = new JsonObject();
        object1.addProperty(NetworkConstants.RESULT, casinoResult);
        object1.addProperty(NetworkConstants.PLAYER, 0);
        object1.addProperty(NetworkConstants.OPERATION, NetworkConstants.SEND_CASINO);

        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());
        LocalBroadcastManager.getInstance(Client.getMapView())
                .sendBroadcast(intent);

        Intent intent2 = new Intent("client.update");
        intent2.putExtra("result", object1.toString());

        LocalBroadcastManager.getInstance(Client.getMapView())
                .sendBroadcast(intent2);

    }
}
