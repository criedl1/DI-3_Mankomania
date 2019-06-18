package com.example.mankomania.network.client;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.mankomania.gamedata.GameData;
import com.example.mankomania.network.NetworkConstants;
import com.example.mankomania.network.QueueHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Queue;

public class ClientQueueHandler extends QueueHandler {
    private Client client;
    private GameData gameData;

    ClientQueueHandler(Queue<String> queue, Client client, GameData gameData) {
        this.queue = queue;
        this.client = client;
        this.gameData = gameData;
    }

    protected void handleMessage(String message) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(message).getAsJsonObject();
        switch (jsonToString(jsonObject,NetworkConstants.OPERATION)) {
            // set ID of the Client
            case NetworkConstants.SET_ID:
                client.setIdx(jsonToInt(jsonObject,NetworkConstants.ID));
                publishUpdate(jsonObject);
                break;
            // Starts the GameData
            case NetworkConstants.SET_PLAYER_COUNT:
                generateGameData(jsonObject);
                break;
            case NetworkConstants.SEND_PLAYER:
                setPlayerId(jsonObject);
                break;
            case NetworkConstants.SEND_MONEY:
                setMoney(jsonObject);
                break;
            case NetworkConstants.SET_POSITION:
                setPosition(jsonObject);
                break;
            case NetworkConstants.SET_HYPO_AKTIE:
                setHypoAktie(jsonObject);
                break;
            case NetworkConstants.SET_STRABAG_AKTIE:
                setStrabagAktie(jsonObject);
                break;
            case NetworkConstants.SET_INFINEON_AKTIE:
                setInfineonAktie(jsonObject);
                break;
            case NetworkConstants.BLAME_RESULT:
                setBlameResult(jsonObject);
                break;
            case NetworkConstants.SUCCESSCHEAT:
                setCheatSuccess(jsonObject);
                break;
            case NetworkConstants.SET_LOTTO:
                setLotto(jsonObject);
                break;
            case NetworkConstants.SET_HOTEL:
                setHotel(jsonObject);
                break;
            case NetworkConstants.ROLL_DICE:
                rollDice(jsonObject);
                break;
            case NetworkConstants.SPIN_WHEEL:
                spinWheel(jsonObject);
                break;
            case NetworkConstants.START_TURN:
                startTurn(jsonObject);
                break;
            case NetworkConstants.GET_ORDER:
                publishUpdate(jsonObject);
                break;
            case NetworkConstants.GAMEEND:
                gameEnd(jsonObject);
                break;
            case NetworkConstants.SEND_CASINO:
                sendCasinoUpdate(jsonObject);
                break;
            default:
                break;
        }
    }

    private void gameEnd(JsonObject jsonObject){
        publishUpdate(jsonObject);
    }

    private void setCheatSuccess(JsonObject jsonObject) {
        publishUpdate(jsonObject);
    }

    private void setBlameResult(JsonObject jsonObject) {
        publishUpdate(jsonObject);
    }

    private void startTurn(JsonObject jsonObject) {
        publishUpdate(jsonObject);
    }
    private void spinWheel(JsonObject jsonObject) {
        publishUpdate(jsonObject);
    }
    private void rollDice(JsonObject jsonObject) {
        publishUpdate(jsonObject);
    }
    private void sendCasinoUpdate(JsonObject json){ publishUpdate(json); }
    private void setHotel(JsonObject jsonObject) {
        int[] arr = gameData.getHotels();
        //Get Values
        int hotel =jsonToInt(jsonObject,NetworkConstants.HOTEL);
        int owner = jsonToInt(jsonObject,NetworkConstants.OWNER);
        //Change GameData
        arr[hotel] = owner;
        gameData.setHotels(arr);
        publishUpdate(jsonObject);
    }
    private void setLotto(JsonObject jsonObject) {
        //Get Values
        int amount = jsonToInt(jsonObject,NetworkConstants.AMOUNT);
        //Change GameData
        gameData.setLotto(amount);
        publishUpdate(jsonObject);
    }

    private void setInfineonAktie(JsonObject jsonObject) {
        int[] arr = gameData.getInfineonAktie();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);
        //Change GameData
        arr[player] = count;
        gameData.setInfineonAktie(arr);
        publishUpdate(jsonObject);
    }
    private void setStrabagAktie(JsonObject jsonObject) {
        int[] arr = gameData.getStrabagAktie();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);
        //Change GameData
        arr[player] = count;
        gameData.setStrabagAktie(arr);
        publishUpdate(jsonObject);
    }
    private void setHypoAktie(JsonObject jsonObject) {
        int[] arr = gameData.getHypoAktie();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);
        //Change GameData
        arr[player] = count;
        gameData.setHypoAktie(arr);
        publishUpdate(jsonObject);
    }
    private void setPosition(JsonObject jsonObject) {
        int[] arr = gameData.getPosition();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int position = jsonToInt(jsonObject, NetworkConstants.POSITION);
        //Change GameData
        arr[player] = position;
        gameData.setPosition(arr);
        publishUpdate(jsonObject);
    }
    private void setMoney(JsonObject jsonObject) {
        int[] arr = gameData.getMoney();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int money = jsonToInt(jsonObject,NetworkConstants.MONEY);
        //Change GameData
        arr[player] = money;
        gameData.setMoney(arr);
        publishUpdate(jsonObject);
    }
    private void setPlayerId(JsonObject jsonObject) {
        String[] arr = gameData.getIpAdresses();
        //Get Values
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        String ip = jsonToString(jsonObject, NetworkConstants.IP);
        //Change GameData
        arr[player] = ip;
        gameData.setIpAdresses(arr);
        publishUpdate(jsonObject);
    }

    private String jsonToString(JsonObject jsonObject, String key){
        return jsonObject.get(key).getAsString();
    }
    private int jsonToInt(JsonObject jsonObject, String key){
        return Integer.parseInt(jsonObject.get(key).getAsString());
    }

    private void generateGameData(JsonObject jsonObject){
        gameData.initEmptyGameData(jsonToInt(jsonObject,NetworkConstants.COUNT));

        publishUpdate(jsonObject);
    }

    private void publishUpdate(JsonObject jsonObject){
        Intent intent = new Intent("client.update");
        intent.putExtra("result", jsonObject.toString());
        LocalBroadcastManager.getInstance(Client.getMapView())
                .sendBroadcast(intent);
    }
}
