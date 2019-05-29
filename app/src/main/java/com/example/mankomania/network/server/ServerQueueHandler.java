package com.example.mankomania.network.server;

import android.util.Log;

import com.example.mankomania.gamedata.GameData;
import com.example.mankomania.map.GameController;
import com.example.mankomania.network.NetworkConstants;
import com.example.mankomania.network.QueueHandler;
import com.example.mankomania.roulette.SendMoneyClass;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Queue;
import java.util.Random;

public class ServerQueueHandler extends QueueHandler {
    private ClientHandler[] clientHandlers;

    private GameData gameData;

    ServerQueueHandler(ClientHandler[] clientHandlers, Queue<String> queue, GameData gameData){
        this.queue = queue;
        this.clientHandlers = clientHandlers;
        this.gameData = gameData;
        this.gameData.setServer(this);
    }

    protected void handleMessage(String in) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(in).getAsJsonObject();

        Log.i("C->S",in);
        switch (jsonToString(jsonObject,NetworkConstants.OPERATION)) {
            case NetworkConstants.SEND_MONEY:
                setMoneyForClients(jsonObject);
                break;
            case NetworkConstants.SET_POSITION:
                setPositionForClients(jsonObject);
                break;
            case NetworkConstants.SET_HYPO_AKTIE:
                setHypoAktieForClients(jsonObject);
                break;
            case NetworkConstants.SET_STRABAG_AKTIE:
                setStrabagAktieForClients(jsonObject);
                break;
            case NetworkConstants.SET_INFINEON_AKTIE:
                setInfineonAktieForClients(jsonObject);
                break;
            case NetworkConstants.SET_CHEATER:
                setCheaterForClients(jsonObject);
                break;
            case NetworkConstants.SET_LOTTO:
                setLottoForClients(jsonObject);
                break;
            case NetworkConstants.SET_HOTEL:
                setHotelForClients(jsonObject);
                break;
            case NetworkConstants.ROLL_DICE:
                rollDiceForClients(jsonObject);
                break;
            case NetworkConstants.SPIN_WHEEL:
                spinWheelForClients(jsonObject);
                break;
            case NetworkConstants.END_TURN:
                endTurn(jsonObject);
                break;
            default:
                break;
        }
    }

    private void endTurn(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject,NetworkConstants.PLAYER);

        player++;
        player = player%gameData.getPlayers().length;

        startTurn(player);
    }
    public void startTurn(int player) {
        gameData.setTurn(player);
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.START_TURN);
        json.addProperty(NetworkConstants.PLAYER, player);
        //Send only one Player
        sendAllClients(json.toString());
    }
    private void spinWheelForClients(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject,NetworkConstants.PLAYER);

            sendSpinResult(player, SendMoneyClass.getMoneyAmount());
    }
    private void sendSpinResult(int idx, int result) {
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SPIN_WHEEL);
        json.addProperty(NetworkConstants.RESULT, result);
        json.addProperty(NetworkConstants.PLAYER, idx);
        sendAllClients(json.toString());
    }
    private void rollDiceForClients(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int result = new Random().nextInt(11)+2;
        gameData.setPosition(player, (gameData.getPosition()[player]+result)% GameController.allfields.length);
        sendPosition(player,gameData.getPosition()[player]);
        sendDiceResult(player, result);
    }
    private void sendDiceResult(int idx,int result) {
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.ROLL_DICE);
        json.addProperty(NetworkConstants.RESULT, result);
        json.addProperty(NetworkConstants.PLAYER, idx);
        sendAllClients(json.toString());
    }
    private void setHotelForClients(JsonObject jsonObject) {
        int[] arr = gameData.getHotels();
        //Get Values
        int hotel =jsonToInt(jsonObject,NetworkConstants.HOTEL);
        int owner = jsonToInt(jsonObject,NetworkConstants.OWNER);
        //Change GameData
        arr[hotel] = owner;
        gameData.setHotels(arr);
        //sendData
        sendHotel(hotel,owner);
    }
    private void setLottoForClients(JsonObject jsonObject) {
        //Get Values
        int amount = jsonToInt(jsonObject,NetworkConstants.AMOUNT);
        //Change GameData
        gameData.setLotto(amount);
        //sendData
        sendLotto(amount);
    }
    private void setCheaterForClients(JsonObject jsonObject) {
        boolean[] arr = gameData.getIsCheater();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        boolean count = (jsonToInt(jsonObject,NetworkConstants.CHEATER)==1);
        //Change GameData
        arr[player] = count;
        gameData.setIsCheater(arr);
        //sendData
        sendCheater(player,count);
    }
    private void setInfineonAktieForClients(JsonObject jsonObject) {
        int[] arr = gameData.getInfineonAktie();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);
        //Change GameData
        arr[player] = count;
        gameData.setInfineonAktie(arr);
        //sendData
        sendInfineonAktie(player,count);
    }
    private void setStrabagAktieForClients(JsonObject jsonObject) {
        int[] arr = gameData.getStrabagAktie();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);
        //Change GameData
        arr[player] = count;
        gameData.setStrabagAktie(arr);
        //sendData
        sendStrabagAktie(player,count);
    }
    private void setHypoAktieForClients(JsonObject jsonObject) {
        int[] arr = gameData.getHypoAktie();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);
        //Change GameData
        arr[player] = count;
        gameData.setHypoAktie(arr);
        //sendData
        sendHypoAktie(player,count);
    }
    private void setPositionForClients(JsonObject jsonObject) {
        int[] arr = gameData.getPosition();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int position = jsonToInt(jsonObject,NetworkConstants.POSITION);
        //Change GameData
        arr[player] = position;
        gameData.setPosition(arr);
        //sendData
        sendPosition(player,position);
    }
    private void setMoneyForClients(JsonObject jsonObject) {
        int[] arr = gameData.getMoney();
        //Get Values
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int money = jsonToInt(jsonObject,NetworkConstants.MONEY);
        //Change GameData
        arr[player] = money;
        gameData.setMoney(arr);

        //sendData
        sendMoney(player,money);
    }

    private void sendAllClients(String message) {
        //send Command to all Clients
        for (ClientHandler client : clientHandlers) {
            client.send(message);
        }
    }

    void sendPlayer(int idx, String str){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SEND_PLAYER);
        json.addProperty(NetworkConstants.PLAYER, idx);
        json.addProperty(NetworkConstants.IP, str);
        sendAllClients(json.toString());
    }
    public void sendMoney(int idx, int money){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SEND_MONEY);
        json.addProperty(NetworkConstants.PLAYER, idx);
        json.addProperty(NetworkConstants.MONEY, money);
        sendAllClients(json.toString());
    }
    public void sendPosition(int idx, int pos){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_POSITION);
        json.addProperty(NetworkConstants.PLAYER, idx);
        json.addProperty(NetworkConstants.POSITION, pos);
        sendAllClients(json.toString());
    }
    private void sendHypoAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.POSITION,NetworkConstants.SET_HYPO_AKTIE);
        json.addProperty(NetworkConstants.PLAYER, idx);
        json.addProperty(NetworkConstants.COUNT, count);
        sendAllClients(json.toString());
    }
    private void sendStrabagAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_STRABAG_AKTIE);
        json.addProperty(NetworkConstants.PLAYER, idx);
        json.addProperty(NetworkConstants.COUNT, count);
        sendAllClients(json.toString());
    }
    private void sendInfineonAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_INFINEON_AKTIE);
        json.addProperty(NetworkConstants.PLAYER, idx);
        json.addProperty(NetworkConstants.COUNT, count);
        sendAllClients(json.toString());
    }
    private void sendCheater(int idx, boolean cheater){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_CHEATER);
        json.addProperty(NetworkConstants.PLAYER, idx);
        json.addProperty(NetworkConstants.CHEATER, cheater);
        sendAllClients(json.toString());
    }
    public void sendLotto(int amount){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_LOTTO);
        json.addProperty(NetworkConstants.AMOUNT, amount);
        sendAllClients(json.toString());
    }
    private void sendHotel(int idx, int owner){
        JsonObject json = new JsonObject();
        json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_HOTEL);
        json.addProperty(NetworkConstants.HOTEL, idx);
        json.addProperty(NetworkConstants.OWNER, owner);
        sendAllClients(json.toString());
    }

    private String jsonToString(JsonObject jsonObject, String key){
        return jsonObject.get(key).getAsString();
    }
    private int jsonToInt(JsonObject jsonObject, String key){
        return Integer.parseInt(jsonObject.get(key).getAsString());
    }
}
