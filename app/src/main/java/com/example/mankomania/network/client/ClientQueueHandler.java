package com.example.mankomania.network.client;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.mankomania.gamedata.GameData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.Queue;

import com.example.mankomania.network.NetworkConstants;

public class ClientQueueHandler extends Thread{
    private Queue<String> queue;
    private Client client;
    private GameData gameData;
    private static String TAG = "ClientQueueHandler";

    ClientQueueHandler(Queue<String> queue, Client client, GameData gameData) {
        this.queue = queue;
        this.client = client;
        this.gameData = gameData;
    }

    @Override
    public void run(){
        String in;
        try{
            while (true){
                // wait for a new message in the queue
                if(!queue.isEmpty()){
                    in = queue.poll();
                    handleMessage(in);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleMessage(String message) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(message).getAsJsonObject();
        Log.i("CLIENT", jsonObject.toString());
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
            case NetworkConstants.SET_CHEATER:
                setCheater(jsonObject);
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
            default:
                break;
        }
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

    private void setCheater(JsonObject jsonObject) {
        boolean[] arr = gameData.getIsCheater();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        boolean count = (jsonToInt(jsonObject,NetworkConstants.CHEATER)==1);
        //Change GameData
        arr[player] = count;
        gameData.setIsCheater(arr);
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
        String[] arr = gameData.getPlayers();
        //Get Values
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        String ip = jsonToString(jsonObject, NetworkConstants.IP);
        //Change GameData
        arr[player] = ip;
        gameData.setPlayers(arr);
    }

    private String jsonToString(JsonObject jsonObject, String key){
        return jsonObject.get(key).getAsString();
    }

    private int jsonToInt(JsonObject jsonObject, String key){
        return Integer.parseInt(jsonObject.get(key).getAsString());
    }

    private void generateGameData(JsonObject jsonObject){
        int playerCount = jsonToInt(jsonObject,NetworkConstants.COUNT);
        int[] intArr = new int[playerCount];
        boolean[] boolArr = new boolean[playerCount];
        String[] strArr = new String[playerCount];

        // Set Player[] (fills in ConnectPlayers)
        Arrays.fill(strArr,"");
        gameData.setPlayers(strArr);

        // Set Arrays with 0
        Arrays.fill(intArr,0);
        gameData.setMoney(intArr);
        gameData.setPosition(intArr);
        gameData.setHypoAktie(intArr);
        gameData.setStrabagAktie(intArr);
        gameData.setInfineonAktie(intArr);

        // Set Array with false
        Arrays.fill(boolArr,false);
        gameData.setIsCheater(boolArr);

        // Set Lotto to 0
        gameData.setLotto(0);

        // Set all Hotel to 0
        intArr = new int[5];
        Arrays.fill(intArr,0);
        gameData.setHotels(intArr);
        publishUpdate(jsonObject);
    }

    private void publishUpdate(JsonObject jsonObject){
        Intent intent = new Intent("client.update");
        intent.putExtra("result", jsonObject.toString());
        LocalBroadcastManager.getInstance(Client.MapView)
                .sendBroadcast(intent);
    }
}
