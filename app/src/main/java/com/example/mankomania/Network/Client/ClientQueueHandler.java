package com.example.mankomania.Network.Client;

import com.example.mankomania.GameData.GameData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.Queue;

public class ClientQueueHandler extends Thread{
    private Queue<String> queue;
    Client client;
    private GameData gameData;

    public ClientQueueHandler(Queue<String> queue, Client client, GameData gameData) {
        this.queue = queue;
        this.client = client;
        this.gameData = gameData;
    }

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

        switch (jsonToString(jsonObject,"OPERATION")) {
            // set ID of the Client
            case "SET_ID":
                client.setIdx(jsonToInt(jsonObject,"ID"));
                break;
            // Starts the GameData
            case "SET_PLAYER_COUNT":
                generateGameData(jsonToInt(jsonObject,"COUNT"));
                break;
            case "sendPlayer":
                setPlayerId(jsonObject);
                break;
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
            case "spinWheel":
                spinWheel(jsonObject);
                break;
            default:
                break;
        }
    }

    private void spinWheel(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject,"Player");
        int result = jsonToInt(jsonObject,"Result");

        // TODO Spin the Wheel on the UI
    }

    private void rollDice(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject,"Player");
        int result = jsonToInt(jsonObject,"Result");

        //TODO Roll the Dices on the UI
    }

    private void setHotel(JsonObject jsonObject) {
        int[] arr = gameData.getHotels();
        //Get Values
        int hotel =jsonToInt(jsonObject,"Hotel");
        int owner = jsonToInt(jsonObject,"Owner");
        //Change GameData
        arr[hotel] = owner;
        gameData.setHotels(arr);

        //TODO Hotel Actions and UI
    }

    private void setLotto(JsonObject jsonObject) {
        //Get Values
        int amount = jsonToInt(jsonObject,"Amount");
        //Change GameData
        gameData.setLotto(amount);

        //TODO Lotto Actions
    }

    private void setCheater(JsonObject jsonObject) {
        boolean[] arr = gameData.getIsCheater();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        boolean count = (jsonToInt(jsonObject,"Cheater")==1);
        //Change GameData
        arr[player] = count;
        gameData.setIsCheater(arr);

        //TODO Cheater Action
    }

    private void setInfineonAktie(JsonObject jsonObject) {
        int[] arr = gameData.getInfineonAktie();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int count = jsonToInt(jsonObject,"Count");
        //Change GameData
        arr[player] = count;
        gameData.setInfineonAktie(arr);

        //TODO Change Player-Aktie on UI
    }

    private void setStrabagAktie(JsonObject jsonObject) {
        int[] arr = gameData.getStrabagAktie();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int count = jsonToInt(jsonObject,"Count");
        //Change GameData
        arr[player] = count;
        gameData.setStrabagAktie(arr);

        //TODO Change Player-Aktie on UI
    }

    private void setHypoAktie(JsonObject jsonObject) {
        int[] arr = gameData.getHypoAktie();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int count = jsonToInt(jsonObject,"Count");
        //Change GameData
        arr[player] = count;
        gameData.setHypoAktie(arr);

        //TODO Change Player-Aktie on UI
    }

    private void setPosition(JsonObject jsonObject) {
        int[] arr = gameData.getPosition();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int position = jsonToInt(jsonObject,"Position");
        //Change GameData
        arr[player] = position;
        gameData.setPosition(arr);

        //TODO Change Player Position on UI
    }

    private void setMoney(JsonObject jsonObject) {
        int[] arr = gameData.getMoney();
        //Get Values
        int player =jsonToInt(jsonObject,"PLAYER");
        int money = jsonToInt(jsonObject,"Money");
        //Change GameData
        arr[player] = money;
        gameData.setMoney(arr);

        // TODO Update UI
    }

    private void setPlayerId(JsonObject jsonObject) {
        String[] arr = gameData.getPlayers();
        //Get Values
        int player = jsonToInt(jsonObject, "PLAYER");
        String ip = jsonToString(jsonObject, "IP");
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

    private void generateGameData(int playerCount){
        int[] int_arr = new int[playerCount];
        boolean[] bool_arr = new boolean[playerCount];
        String[] str_arr = new String[playerCount];

        // Set Player[] (fills in ConnectPlayers)
        Arrays.fill(str_arr,"");
        gameData.setPlayers(str_arr);

        // Set Arrays with 0
        Arrays.fill(int_arr,0);
        gameData.setMoney(int_arr);
        gameData.setPosition(int_arr);
        gameData.setHypoAktie(int_arr);
        gameData.setStrabagAktie(int_arr);
        gameData.setInfineonAktie(int_arr);

        // Set Array with false
        Arrays.fill(bool_arr,false);
        gameData.setIsCheater(bool_arr);

        // Set Lotto to 0
        gameData.setLotto(0);

        // Set all Hotel to 0
        int_arr = new int[5];
        Arrays.fill(int_arr,0);
        gameData.setHotels(int_arr);
    }
}
