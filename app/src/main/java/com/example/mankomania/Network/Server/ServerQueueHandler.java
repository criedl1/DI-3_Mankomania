package com.example.mankomania.Network.Server;

import com.example.mankomania.GameData.GameData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Queue;

public class ServerQueueHandler extends Thread{
    ClientHandler[] clientHandlers;
    private Queue<String> queue;
    private GameData gameData;

    public ServerQueueHandler(ClientHandler[] clientHandlers, Queue queue, GameData gameData){
        this.queue = queue;
        this.clientHandlers = clientHandlers;
        this.gameData = gameData;
    }

    public void run(){
        String in;
        try{
            while(true){
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

    private void handleMessage(String in) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(in).getAsJsonObject();

        switch (jsonToString(jsonObject,"OPERATION")) {
            case "sendMoney":
                setMoney(jsonObject);
                break;
            case "setPosition":
                setPosition(jsonObject);
                break;
            case "setHypoAktie":
                setHypoAktie(jsonObject);
                break;
            case "setStrabagAktie":
                setStrabagAktie(jsonObject);
                break;
            case "setInfineonAktie":
                setInfineonAktie(jsonObject);
                break;
            case "setCheater":
                setCheater(jsonObject);
                break;
            case "setLotto":
                setLotto(jsonObject);
                break;
            case "setHotel":
                setHotel(jsonObject);
                break;
            case "rollDice":
                rollDice(jsonObject);
                break;
            default:
                break;
        }
    }

    private void rollDice(JsonObject jsonObject) {
        //TODO Roll the Dice ServerSide
        int player = jsonToInt(jsonObject,"Player");
        sendDiceResult(player, 7);
    }

    private void sendDiceResult(int idx,int result) {
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","rollDice");
        json.addProperty("Result", result);
        json.addProperty("Player", idx);
        sendAllClients(json.toString());
    }

    private void setHotel(JsonObject jsonObject) {
        int[] arr = gameData.getHotels();
        //Get Values
        int hotel =jsonToInt(jsonObject,"Hotel");
        int owner = jsonToInt(jsonObject,"Owner");
        //Change GameData
        arr[hotel] = owner;
        gameData.setHotels(arr);
        //sendData
        sendHotel(hotel,owner);
    }

    private void setLotto(JsonObject jsonObject) {
        //Get Values
        int amount = jsonToInt(jsonObject,"Amount");
        //Change GameData
        gameData.setLotto(amount);
        //sendData
        sendLotto(amount);
    }

    private void setCheater(JsonObject jsonObject) {
        boolean[] arr = gameData.getIsCheater();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        boolean count = (jsonToInt(jsonObject,"Cheater")==1);
        //Change GameData
        arr[player] = count;
        gameData.setIsCheater(arr);
        //sendData
        sendCheater(player,count);
    }

    private void setInfineonAktie(JsonObject jsonObject) {
        int[] arr = gameData.getInfineonAktie();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int count = jsonToInt(jsonObject,"Count");
        //Change GameData
        arr[player] = count;
        gameData.setInfineonAktie(arr);
        //sendData
        sendInfineonAktie(player,count);
    }

    private void setStrabagAktie(JsonObject jsonObject) {
        int[] arr = gameData.getStrabagAktie();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int count = jsonToInt(jsonObject,"Count");
        //Change GameData
        arr[player] = count;
        gameData.setStrabagAktie(arr);
        //sendData
        sendStrabagAktie(player,count);
    }

    private void setHypoAktie(JsonObject jsonObject) {
        int[] arr = gameData.getHypoAktie();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int count = jsonToInt(jsonObject,"Count");
        //Change GameData
        arr[player] = count;
        gameData.setHypoAktie(arr);
        //sendData
        sendHypoAktie(player,count);
    }

    private void setPosition(JsonObject jsonObject) {
        int[] arr = gameData.getPosition();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int position = jsonToInt(jsonObject,"Position");
        //Change GameData
        arr[player] = position;
        gameData.setPosition(arr);
        //sendData
        sendPosition(player,position);
    }

    private void setMoney(JsonObject jsonObject) {
        int[] arr = gameData.getMoney();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int money = jsonToInt(jsonObject,"Money");
        //Change GameData
        arr[player] = money;
        gameData.setMoney(arr);
        //sendData
        sendMoney(player,money);
    }

    public void sendAllClients(String message) {
        //send Command to all Clients
        for (ClientHandler client : clientHandlers) {
            client.send(message);
        }
    }

    public void sendPlayer(int idx, String str){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","sendPlayer");
        json.addProperty("PLAYER", idx);
        json.addProperty("IP", str);
        sendAllClients(json.toString());
    }

    public void sendMoney(int idx, int money){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","sendMoney");
        json.addProperty("PLAYER", idx);
        json.addProperty("Money", money);
        sendAllClients(json.toString());
    }

    public void sendPosition(int idx, int pos){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setPosition");
        json.addProperty("PLAYER", idx);
        json.addProperty("Position", pos);
        sendAllClients(json.toString());
    }

    public void sendHypoAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setHypoAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        sendAllClients(json.toString());
    }

    public void sendStrabagAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setStrabagAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        sendAllClients(json.toString());
    }

    public void sendInfineonAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setInfineonAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        sendAllClients(json.toString());
    }

    public void sendCheater(int idx, boolean cheater){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setCheater");
        json.addProperty("PLAYER", idx);
        json.addProperty("Cheater", cheater);
        sendAllClients(json.toString());
    }

    public void sendLotto(int amount){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setLotto");
        json.addProperty("Amount", amount);
        sendAllClients(json.toString());
    }

    public void sendHotel(int idx, int owner){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setHotel");
        json.addProperty("Hotel", idx);
        json.addProperty("Owner", owner);
        sendAllClients(json.toString());
    }

    private String jsonToString(JsonObject jsonObject, String key){
        return jsonObject.get(key).getAsString();
    }

    private int jsonToInt(JsonObject jsonObject, String key){
        return Integer.parseInt(jsonObject.get(key).getAsString());
    }
}
